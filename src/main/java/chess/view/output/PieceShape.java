package chess.view.output;

import chess.model.piece.*;

import java.util.Arrays;

public enum PieceShape {
    WHITE_BISHOP("b", Bishop.from(Side.WHITE)),
    BLACK_BISHOP("B", Bishop.from(Side.BLACK)),
    WHITE_KING("k", King.from(Side.WHITE)),
    BLACK_KING("K", King.from(Side.BLACK)),
    WHITE_KNIGHT("n", Knight.from(Side.WHITE)),
    BLACK_KNIGHT("N", Knight.from(Side.BLACK)),
    WHITE_PAWN("p", Pawn.from(Side.WHITE)),
    BLACK_PAWN("P", Pawn.from(Side.BLACK)),
    WHITE_QUEEN("q", Queen.from(Side.WHITE)),
    BLACK_QUEEN("Q", Queen.from(Side.BLACK)),
    WHITE_ROOK("r", Rook.from(Side.WHITE)),
    BLACK_ROOK("R", Rook.from(Side.BLACK)),
    BLANK(".", Blank.INSTANCE);

    private final String shape;
    private final Piece piece;

    PieceShape(String shape, Piece piece) {
        this.shape = shape;
        this.piece = piece;
    }

    public static PieceShape from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceShape -> pieceShape.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Piece의 출력 모양을 찾을 수 없습니다."));
    }

    public String getShape() {
        return shape;
    }
}
