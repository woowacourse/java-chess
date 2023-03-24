package chess.domain.game;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.dto.BoardDto;

import java.util.List;
import java.util.Map;


public final class ChessGame {

    private static final int BOARD_LENGTH = 8;
    public static final double TOTAL_BOARD_SIZE = Math.pow(BOARD_LENGTH, 2);

    private final Map<Position, Piece> board;
    private Turn turn;

    private ChessGame(final Map<Position, Piece> board) {
        this.board = board;
        this.turn = Turn.create();
    }

    public static ChessGame from(final Map<Position, Piece> board) {
        if (board.size() != TOTAL_BOARD_SIZE) {
            throw new IllegalArgumentException(
                    String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_LENGTH, BOARD_LENGTH));
        }
        return new ChessGame(board);
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

    public BoardDto getBoard() {
        return BoardDto.from(Map.copyOf(board));
    }
}
