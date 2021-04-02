package chess.domain.piece;

import chess.domain.piece.attribute.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ColoredPieces {
    private static final int PAWN_SIZE = 8;

    private final List<Piece> pieces;
    private final Color color;

    public ColoredPieces(List<Piece> pieces, Color color) {
        this.pieces = pieces;
        this.color = color;
    }

    public static ColoredPieces createByColor(Color color) {
        ArrayList<Piece> pieces = new ArrayList<>(Arrays.asList(
                new King(color), new Queen(color),
                new Bishop(color), new Bishop(color),
                new Rook(color), new Rook(color),
                new Knight(color), new Knight(color)));
        pieces.addAll(createPawnsByColor(color));
        return new ColoredPieces(pieces, color);
    }

    private static List<Piece> createPawnsByColor(Color color) {
        return IntStream.range(0, PAWN_SIZE)
                .mapToObj(i -> new Pawn(color))
                .collect(Collectors.toList());
    }

    public void remove(Piece piece) {
        if (piece.getColor() != color) {
            throw new IllegalArgumentException("진영이 다른 피스를 제거할 수 없습니다");
        }
        pieces.remove(piece);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isKingDead() {
        return !pieces.contains(new King(color));
    }

    public boolean isKingAlive() {
        return pieces.contains(new King(color));
    }

    public int size() {
        return pieces.size();
    }

    public boolean contains(Piece piece) {
        return pieces.contains(piece);
    }

    public Color getColor() {
        return this.color;
    }
}
