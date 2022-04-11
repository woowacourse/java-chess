package chess.domain.piece;

import java.util.Arrays;

public enum PieceFactory {

    WHITE_PAWN(new Pawn(Color.WHITE)),
    WHITE_ROOK(new Rook(Color.WHITE)),
    WHITE_BISHOP(new Bishop(Color.WHITE)),
    WHITE_KNIGHT(new Knight(Color.WHITE)),
    WHITE_QUEEN(new Queen(Color.WHITE)),
    WHITE_KING(new King(Color.WHITE)),

    BLACK_PAWN(new Pawn(Color.BLACK)),
    BLACK_ROOK(new Rook(Color.BLACK)),
    BLACK_BISHOP(new Bishop(Color.BLACK)),
    BLACK_KNIGHT(new Knight(Color.BLACK)),
    BLACK_QUEEN(new Queen(Color.BLACK)),
    BLACK_KING(new King(Color.BLACK)),
    ;

    private final Piece piece;

    PieceFactory(final Piece piece) {
        this.piece = piece;
    }

    public static Piece of(final String name, final String color) {
        return Arrays.stream(values())
                .filter(pieceFactory -> pieceFactory.name().contains(name))
                .filter(pieceFactory -> pieceFactory.name().contains(color))
                .map(pieceFactory -> pieceFactory.piece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }
}
