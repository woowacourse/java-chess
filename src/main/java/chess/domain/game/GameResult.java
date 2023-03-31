package chess.domain.game;


import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.RoleType;
import chess.domain.position.FileCoordinate;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private static final int SAME_FILE_PAWN_COUNT = 2;
    private static final double SAME_FILE_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> boards;

    public GameResult(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    public double calculateScore(Color color) {
        return calculatePieceScore(color) - calculatePawnScore(color);
    }

    private double calculatePieceScore(Color color) {
        return boards.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculatePawnScore(Color color) {
        Map<FileCoordinate, Long> countByFile = boards.keySet().stream()
                .filter(position -> boards.get(position).isSameColor(color))
                .filter(position -> boards.get(position).isSameRoleType(RoleType.PAWN))
                .collect(Collectors.groupingBy(Position::getFileCoordinate, Collectors.counting()));

        return countByFile.values().stream()
                .filter(count -> count >= SAME_FILE_PAWN_COUNT)
                .mapToDouble(count -> count * SAME_FILE_PAWN_SCORE)
                .sum();
    }

    public final Color getWinner() {
        if (isKingDead(Color.WHITE) || isKingDead(Color.BLACK)) {
            return calculateByKing();
        }
        return calculateByScore();
    }

    private boolean isKingDead(Color color) {
        return boards.values().stream()
                .filter(piece -> piece.isSameRoleType(RoleType.KING))
                .noneMatch(piece -> piece.isSameColor(color));
    }

    private Color calculateByKing() {
        if (isKingDead(Color.WHITE)) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    private Color calculateByScore() {
        if (calculateScore(Color.WHITE) == calculateScore(Color.BLACK)) {
            return Color.EMPTY;
        }
        if (calculateScore(Color.WHITE) > calculateScore(Color.BLACK)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Map<Position, Piece> board() {
        return boards;
    }
}
