package chess;

public enum PieceSign {

    PAWN(Piece.class, "p");

    private final Class<Piece> pieceClass;
    private final String sign;

    PieceSign(Class<Piece> pieceClass, String sign) {
        this.pieceClass = pieceClass;
        this.sign = sign;
    }

    public static String findSign(Piece piece) {
        for (PieceSign value : values()) {
            if (piece.getClass() == value.pieceClass) {
                return value.sign;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 piece입니다.");
    }
}
