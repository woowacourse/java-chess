package chess;

public enum PieceSign {

    PAWN(Pawn.class, "p"),
    ;

    private final Class<? extends Piece> pieceClass;
    private final String sign;

    PieceSign(Class<? extends Piece> pieceClass, String sign) {
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
