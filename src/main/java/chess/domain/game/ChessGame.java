package chess.domain.game;

import chess.dao.ChessDao;
import chess.dao.DbChessGameDao;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.dto.outputView.PrintBoardDto;
import chess.dto.outputView.PrintTotalScoreDto;
import chess.dto.outputView.PrintWinnerDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chess.domain.piece.pawn.Pawn.DEGRADED_SCORE;
import static java.util.stream.Collectors.groupingBy;


public final class ChessGame {

    public static final int COUNT_OF_PAWN_DEGRADE_SCORE = 2;
    private static final int BOARD_LENGTH = 8;
    public static final double TOTAL_BOARD_SIZE = Math.pow(BOARD_LENGTH, 2);

    private final Map<Position, Piece> board;
    private final ChessDao dao;
    private Turn turn;



    private ChessGame(final Map<Position, Piece> board, final Turn turn) {
        this.board = board;
        this.dao = new DbChessGameDao();
        this.turn = turn;
    }

    public static ChessGame from(final Map<Position, Piece> board) {
        if (board.size() != TOTAL_BOARD_SIZE) {
            throw new IllegalArgumentException(
                    String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_LENGTH, BOARD_LENGTH));
        }
        return new ChessGame(board, Turn.create());
    }

    public static ChessGame from(final Map<Position, Piece> board, final Turn turn) {
        if (board.size() != TOTAL_BOARD_SIZE) {
            throw new IllegalArgumentException(
                    String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_LENGTH, BOARD_LENGTH));
        }
        return new ChessGame(board, turn);
    }

    public void move(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateEmptyPiece(sourcePiece);
        validateTurn(sourcePiece);
        validatePath(sourcePiece.findPath(source, target, targetPiece.getTeam()));
        switchPiece(source, target, sourcePiece);
        turn = turn.next();
    }

    private void validateEmptyPiece(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("기물이 없는 곳을 선택하셨습니다.");
        }
    }

    private void validateTurn(final Piece piece) {
        if (!turn.isValidTurn(piece)) {
            throw new IllegalStateException("턴이 올바르지 않습니다. 현재 턴 : " + turn.getTeam());
        }
    }

    private void validatePath(final List<Position> path) {
        final boolean result = path.stream()
                .allMatch(position -> board.get(position).isEmpty());
        if (!result) {
            throw new IllegalArgumentException("이동하려는 경로에 기물이 존재합니다.");
        }
    }

    private void switchPiece(final Position source, final Position target, final Piece sourcePiece) {
        board.put(target, checkInitialPawn(sourcePiece));
        board.put(source, Empty.instance());
    }

    private Piece checkInitialPawn(final Piece piece) {
        if (piece.isInitialPawn() && piece.isWhite()) {
            return WhitePawn.instance();
        }
        if (piece.isInitialPawn() && piece.isBlack()) {
            return BlackPawn.instance();
        }
        return piece;
    }

    public PrintTotalScoreDto calculateScore() {
        return PrintTotalScoreDto.from(calculateScoreByTeam(Team.WHITE), calculateScoreByTeam(Team.BLACK));
    }

    public double calculateScoreByTeam(final Team team) {
        final double totalScore = board.values()
                .stream()
                .filter(piece -> piece.isSameTeamWith(team))
                .mapToDouble(Piece::getScore)
                .sum();

        final Map<File, Long> pieceCountByFile = board.entrySet()
                .stream()
                .filter(entry -> isPawn(entry.getValue()))
                .filter(entry -> entry.getValue().isSameTeamWith(team))
                .collect(groupingBy(e -> e.getKey().getFile(), Collectors.counting()));

        final double totalMinusScore = pieceCountByFile.values()
                .stream()
                .filter(entries -> entries >= COUNT_OF_PAWN_DEGRADE_SCORE)
                .mapToDouble(entries -> entries * DEGRADED_SCORE)
                .sum();

        return totalScore - totalMinusScore;
    }

    private boolean isPawn(final Piece value) {
        return value.isSamePieceTypeAs(PieceType.INITIAL_WHITE_PAWN) ||
                value.isSamePieceTypeAs(PieceType.INITIAL_BLACK_PAWN) ||
                value.isSamePieceTypeAs(PieceType.WHITE_PAWN) ||
                value.isSamePieceTypeAs(PieceType.BLACK_PAWN);
    }

    private Stream<Map.Entry<Position, Piece>> getEntryStream() {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSamePieceTypeAs(PieceType.KING));
    }

    public boolean isKingDead() {
        return getEntryStream()
                .count() < 2;
    }

    public PrintWinnerDto getWinner() {
        final Team team = getEntryStream()
                .map(m -> m.getValue().getTeam())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("왕이 없을 수는 없습니다."));
        return new PrintWinnerDto(team);
    }

    public PrintBoardDto getBoard() {
        return new PrintBoardDto(Map.copyOf(board));
    }

    public Team getTurn() {
        return turn.getTeam();
    }
}
