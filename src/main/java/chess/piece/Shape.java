package chess.piece;

import java.util.stream.Stream;

public enum Shape {

    PAWN("p", "P", 1),
    KNIGHT("n", "N", 2.5),
    BISHOP("b", "B", 3),
    ROOK("r", "R", 5),
    QUEEN("q", "Q", 9),
    KING("k", "K", 0),
    EMPTY(".", ".", 0);

    private final String whiteName;
    private final String blackName;
    private final double score;

    Shape(String whiteName, String blackName, double score) {
        this.whiteName = whiteName;
        this.blackName = blackName;
        this.score = score;
    }

    public static ChessPiece makePieceByShape(Shape inputShape, Side inputSide) {
        if (inputShape.equals(PAWN)) {
            return new Pawn(inputSide);
        }
        if (inputShape.equals(ROOK)) {
            return new Rook(inputSide);
        }
        if (inputShape.equals(KNIGHT)) {
            return new Knight(inputSide);
        }
        if (inputShape.equals(BISHOP)) {
            return new Bishop(inputSide);
        }
        if (inputShape.equals(KING)) {
            return new King(inputSide);
        }
        if (inputShape.equals(QUEEN)) {
            return new Queen(inputSide);
        }
        if (inputShape.equals(EMPTY)) {
            return new Empty(inputSide);
        }
        throw new IllegalArgumentException("해당 Shape에 맞는 기물을 생성할 수 없습니다.");
    }


    public static Shape findShape(String input) {
        return Stream.of(Shape.values())
                .filter(shape -> shape.name().equals(input))
                .findFirst()
                .orElse(null);
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getBlackName() {
        return blackName;
    }

    public double getScore() {
        return score;
    }
}
