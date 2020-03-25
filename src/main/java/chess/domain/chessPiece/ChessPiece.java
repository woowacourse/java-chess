package chess.domain.chessPiece;

import chess.domain.chessPiece.pieceType.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessPiece {
    private final static Map<String, ChessPiece> PIECES = new HashMap<>();

    private final PieceColor pieceColor;
    private final PieceType pieceType;

    private ChessPiece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public static ChessPiece of(PieceColor pieceColor, PieceType pieceType) {
        Objects.requireNonNull(pieceColor, "체스 피스의 컬러가 존재하지 않습니다.");
        Objects.requireNonNull(pieceType, "체스 피스의 타입이 존재하지 않습니다.");

        ChessPiece chessPiece = PIECES.get(key(pieceColor, pieceType));

        if (Objects.isNull(chessPiece)) {
            chessPiece = new ChessPiece(pieceColor, pieceType);
            PIECES.put(key(pieceColor, pieceType), chessPiece);
        }
        return chessPiece;
    }

    private static String key(PieceColor pieceColor, PieceType pieceType) {
        return pieceColor.convertName(pieceType.getName());
    }

    @Override
    public String toString() {
        return key(this.pieceColor, this.pieceType);
    }
}
