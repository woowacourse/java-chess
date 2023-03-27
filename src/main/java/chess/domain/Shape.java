package chess.domain;


public enum Shape {

    WHITE_PAWN("p"),
    BLACK_PAWN("P"),
    WHITE_ROOK("r"),
    BLACK_ROOK("R"),
    WHITE_KNIGHT("n"),
    BLACK_KNIGHT("N"),
    WHITE_BISHOP("b"),
    BLACK_BISHOP("B"),
    WHITE_QUEEN("q"),
    BLACK_QUEEN("Q"),
    WHITE_KING("k"),
    BLACK_KING("K"),
    BLANK(".");

    private final String shape;

    Shape(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }

    //    WHITE_PAWN(new Pawn(Side.WHITE), "p"),
//    BLACK_PAWN(new Pawn(Side.BLACK), "P"),
//    WHITE_KNIGHT(new Knight(Side.WHITE), "n"),
//    BLACK_KNIGHT(new Knight(Side.BLACK), "N"),
//    WHITE_BISHOP(new Bishop(Side.WHITE), "b"),
//    BLACK_BISHOP(new Bishop(Side.BLACK), "B"),
//    WHITE_ROOK(new Rook(Side.WHITE), "r"),
//    BLACK_ROOK(new Rook(Side.BLACK), "R"),
//    WHITE_QUEEN(new Queen(Side.WHITE), "q"),
//    BLACK_QUEEN(new Queen(Side.BLACK), "Q"),
//    WHITE_KING(new King(Side.WHITE), "k"),
//    BLACK_KING(new King(Side.BLACK), "K"),
//    BLANK(new Empty(Side.BLANK), ".");
//
//    private final ChessPiece chessType;
//    private final String name;
//
//    Shape(ChessPiece chessPiece, String name) {
//        this.chessType = chessPiece;
//        this.name = name;
//    }
//
//    public static String getNameByClass(ChessPiece chessPiece) {
//        return Arrays.stream(Shape.values())
//                .filter(shape -> shape.chessType.equals(chessPiece))
//                .findFirst()
//                .map(Shape::getName)
//                .orElse(BLANK.name);
//    }
//
//    public String getName() {
//        return name;
//    }
}
