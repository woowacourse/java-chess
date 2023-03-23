package chess.view;

import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceSymbolConverter {
    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    ROOK(PieceType.ROOK, "r"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    PAWN(PieceType.PAWN, "p"),
    EMPTY(PieceType.EMPTY, "e");
    
    private final PieceType pieceType;
    private final String symbol;
    
    PieceSymbolConverter(PieceType pieceType, String symbol) {
        this.pieceType = pieceType;
        this.symbol = symbol;
    }
    
    public static PieceSymbolConverter from(PieceType pieceType) {
        return Arrays.stream(values())
                .filter(pieceSymbolConverter -> pieceSymbolConverter.isSamePieceType(pieceType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 PieceType 입니다."));
    }
    
    public static String convert(PieceType pieceType) {
        return from(pieceType).symbol;
    }
    
    private boolean isSamePieceType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }
}
