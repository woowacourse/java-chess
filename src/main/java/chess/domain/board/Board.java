package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.normal.*;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public final class Board {

    private static final int STACKED_PAWN = 2;
    private static final double STACKED_PAWN_SCORE = 0.5;
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board initializeBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(File.A, Rank.ONE), new Rook(Color.WHITE));
        board.put(new Position(File.B, Rank.ONE), new Knight(Color.WHITE));
        board.put(new Position(File.C, Rank.ONE), new Bishop(Color.WHITE));
        board.put(new Position(File.D, Rank.ONE), new Queen(Color.WHITE));
        board.put(new Position(File.E, Rank.ONE), new King(Color.WHITE));
        board.put(new Position(File.F, Rank.ONE), new Bishop(Color.WHITE));
        board.put(new Position(File.G, Rank.ONE), new Knight(Color.WHITE));
        board.put(new Position(File.H, Rank.ONE), new Rook(Color.WHITE));

        board.put(new Position(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        board.put(new Position(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        board.put(new Position(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        board.put(new Position(File.D, Rank.EIGHT), new Queen(Color.BLACK));
        board.put(new Position(File.E, Rank.EIGHT), new King(Color.BLACK));
        board.put(new Position(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
        board.put(new Position(File.G, Rank.EIGHT), new Knight(Color.BLACK));
        board.put(new Position(File.H, Rank.EIGHT), new Rook(Color.BLACK));

        board.put(new Position(File.A, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.B, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.C, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.D, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.E, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.F, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.G, Rank.SEVEN), new Pawn(Color.BLACK));
        board.put(new Position(File.H, Rank.SEVEN), new Pawn(Color.BLACK));

        board.put(new Position(File.A, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.B, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.C, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.D, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.E, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.F, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.G, Rank.TWO), new Pawn(Color.WHITE));
        board.put(new Position(File.H, Rank.TWO), new Pawn(Color.WHITE));
        return new Board(board);
    }

    public void confirmMove(final Position source, final Position target, Color color) {
        validateBasicInfo(color, source, target);

        Set<Position> movablePath = getPiece(source).computePath(source, target);
        Map<Position, Boolean> isEmptySquare = generateIsEmptySquare(movablePath);

        validateMove(source, target, isEmptySquare);
        move(source, target);
    }

    private Map<Position, Boolean> generateIsEmptySquare(final Set<Position> movablePath) {
        return movablePath.stream()
                .collect(toMap(
                        position -> position,
                        position -> getPiece(position).isEmpty()
                ));
    }

    private void validateBasicInfo(final Color color, final Position source, final Position target) {
        validateIsEmptySquare(source);
        validateLegalSourceColor(source, color);
        validateLegalTargetColor(source, target);
    }

    private void validateIsEmptySquare(final Position source) {
        if (getPiece(source).isEmpty()) {
            throw new IllegalArgumentException("비어있는 칸입니다.");
        }

    }

    private void validateLegalSourceColor(final Position source, final Color color) {
        if (getPiece(source).differsColor(color)) {
            throw new IllegalArgumentException("움직일 수 있는 기물이 아닙니다.");
        }
    }

    private void validateLegalTargetColor(final Position source, final Position target) {
        if (getPiece(source).equalsColor(getPiece(target))) {
            throw new IllegalArgumentException("자신의 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final Map<Position, Boolean> isEmptySquare) {
        if (!getPiece(source).canMove(isEmptySquare, source, target)) {
            throw new IllegalArgumentException("유효한 움직임이 아닙니다.");
        }
    }

    private void move(final Position source, final Position target) {
        board.put(target, getPiece(source));
        board.remove(source);
    }

    public double computeScore(final Color color) {
        double sum = board.values().stream()
                .filter(piece -> piece.equalsColor(color))
                .mapToDouble(Piece::getScore).sum();

        for (File value : File.values()) {
            long count = board.keySet().stream()
                    .filter(position -> board.get(position).equalsColor(color))
                    .filter(position -> position.isFileEquals(value))
                    .filter(position -> board.get(position).getKind() == Kind.PAWN)
                    .count();
            sum = minusStackPawnPoint(sum, count);
        }
        return sum;
    }

    private double minusStackPawnPoint(double sum, final long count) {
        if (count >= STACKED_PAWN) {
            sum = sum - count * STACKED_PAWN_SCORE;
        }
        return sum;
    }

    public Color computeWinner() {
        List<Color> kingColors = board.values().stream()
                .filter(piece -> piece.getKind() == Kind.KING)
                .map(Piece::getColor)
                .collect(Collectors.toList());

        if (kingColors.size() == 2) {
            return Color.NONE;
        }
        return kingColors.get(0);
    }

    private Piece getPiece(Position position) {
        return board.getOrDefault(position, new Empty());
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
