package chess.utils;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.function.Supplier;

public enum CharToPiece {

    BLACK_PAWN('P', () -> new Pawn(Color.BLACK)),
    WHITE_PAWN('p', () -> new Pawn(Color.WHITE)),
    BLACK_ROOK('R', () -> new Rook(Color.BLACK)),
    WHITE_ROOK('r', () -> new Rook(Color.WHITE)),
    BLACK_KNIGHT('N', () -> new Knight(Color.BLACK)),
    WHITE_KNIGHT('n', () -> new Knight(Color.WHITE)),
    BLACK_BISHOP('B', () -> new Bishop(Color.BLACK)),
    WHITE_BISHOP('b', () -> new Bishop(Color.WHITE)),
    BLACK_QUEEN('Q', () -> new Queen(Color.BLACK)),
    WHITE_QUEEN('q', () -> new Queen(Color.WHITE)),
    BLACK_KING('K', () -> new King(Color.BLACK)),
    WHITE_KING('k', () -> new King(Color.WHITE));

    private final char pieceName;
    private final Supplier<Piece> createPiece;

    CharToPiece(char pieceName, Supplier<Piece> createPiece) {
        this.pieceName = pieceName;
        this.createPiece = createPiece;
    }

    public static Piece of(char pieceName) {
        return Arrays.stream(values())
                .filter(charToPiece -> charToPiece.getPieceName() == pieceName)
                .findFirst()
                .map(charToPiece -> charToPiece.createPiece.get())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 객체를 생성할 수 없습니다."));
    }

    public char getPieceName() {
        return pieceName;
    }
}
