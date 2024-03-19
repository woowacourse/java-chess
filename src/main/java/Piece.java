public enum Piece {

    BLACK_ROOK(Side.BLACK),
    WHITE_ROOK(Side.WHITE),
    ;

    private final Side side;

    Piece(Side side) {
        this.side = side;
    }
}
