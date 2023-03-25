package chess.domain;

import chess.constant.ExceptionCode;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ChessGame {

    private static final Map<PieceType, Double> SCORE_BY_PIECE_TYPE;

    static {
        SCORE_BY_PIECE_TYPE = new EnumMap<>(Map.ofEntries(
                Map.entry(PieceType.KING, 0.0),
                Map.entry(PieceType.QUEEN, 9.0),
                Map.entry(PieceType.BISHOP, 3.0),
                Map.entry(PieceType.KNIGHT, 2.5),
                Map.entry(PieceType.ROOK, 5.0),
                Map.entry(PieceType.PAWN, 0.5)
        ));
    }

    private final Board board;
    private Color currentTurnColor;

    private ChessGame(final Board board) {
        this.board = board;
        this.currentTurnColor = Color.WHITE;
    }

    public static ChessGame createWith(final PiecesGenerator piecesGenerator) {
        return new ChessGame(Board.createBoardWith(piecesGenerator));
    }

    public void move(final Position currentPosition, final Position targetPosition) {
        validateTurnColor(currentPosition);
        board.move(currentPosition, targetPosition);
        changeTurnColor();
    }

    private void validateTurnColor(final Position currentPosition) {
        if (!board.isSameColor(currentPosition, currentTurnColor)) {
            throw new IllegalArgumentException(ExceptionCode.INVALID_TURN.name());
        }
    }

    private void changeTurnColor() {
        currentTurnColor = currentTurnColor.getOppositeColor();
    }

    public boolean isKingCaught() {
        return !(board.isKingExist(currentTurnColor) && board.isKingExist(currentTurnColor.getOppositeColor()));
    }

    public GameStatus getStatus() {
        final Color winningTeamColor = findWinningTeamColor();
        final double blackScore = (winningTeamColor.equals(Color.BLANK)) ? calculateScoreOf(Color.BLACK) : 0.0;
        final double whiteScore = (winningTeamColor.equals(Color.BLANK)) ? calculateScoreOf(Color.WHITE) : 0.0;

        return new GameStatus(winningTeamColor, blackScore, whiteScore);
    }

    private Color findWinningTeamColor() {
        if (!isKingCaught()) {
            return Color.BLANK;
        }
        if (board.isKingExist(Color.BLACK)) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    private double calculateScoreOf(final Color color) {
        final List<Piece> pieces = board.getExistingPieces().stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());

        double score = 0.0;
        for (File file : File.values()) {
            score += calculateScoreIn(countPiecesIn(pieces, file));
        }
        return score;
    }

    private Map<PieceType, Integer> countPiecesIn(final List<Piece> pieces, final File file) {
        final Map<PieceType, Integer> pieceTypeCounts = new HashMap<>();

        for (final Piece piece : filterByFile(pieces, file)) {
            final PieceType pieceType = PieceType.findByPiece(piece);
            final Integer prevCount = pieceTypeCounts.getOrDefault(pieceType, 0);
            pieceTypeCounts.put(pieceType, prevCount + 1);
        }

        return pieceTypeCounts;
    }

    private static List<Piece> filterByFile(final List<Piece> pieces, final File file) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().getFile() == file)
                .collect(Collectors.toUnmodifiableList());
    }

    private double calculateScoreIn(final Map<PieceType, Integer> pieceTypeCounts) {
        return pieceTypeCounts.entrySet().stream()
                .mapToDouble(this::calculateScore)
                .sum();
    }

    private double calculateScore(final Map.Entry<PieceType, Integer> entry) {
        final PieceType pieceType = entry.getKey();
        final int count = entry.getValue();
        final Double unitScore = SCORE_BY_PIECE_TYPE.get(pieceType);
        final double score = unitScore * count;
        if (pieceType == PieceType.PAWN && count == 1) {
            return score * 2;
        }
        return score;
    }

    public Set<Piece> getExistingPieces() {
        return board.getExistingPieces();
    }
}
