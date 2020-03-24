package chess.domain.chessPiece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Piece {
    private final static Map<String, Piece> PIECES = new HashMap<>();

    private final PieceColor pieceColor;
    private final PieceType pieceType;

    public Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public static Piece of(PieceColor pieceColor, PieceType pieceType) {
        Piece piece = PIECES.get(key(pieceColor, pieceType));

        if (Objects.isNull(piece)) {
            piece = new Piece(pieceColor, pieceType);
            PIECES.put(key(pieceColor, pieceType), piece);
        }
        return piece;
    }

    private static String key(PieceColor pieceColor, PieceType pieceType) {
        return pieceColor.convertName(pieceType.getName());
    }


}
