package view.mapper;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.None;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Arrays;

public enum PieceOutput {

    BISHOP(Bishop.class, "b"),
    KING(King.class, "k"),
    KNIGHT(Knight.class, "n"),
    PAWN(Pawn.class, "p"),
    QUEEN(Queen.class, "q"),
    ROOK(Rook.class, "r"),
    NONE(None.class, "."),
    ;

    private final Class<? extends Piece> type;
    private final String output;

    PieceOutput(Class<? extends Piece> type, String output) {
        this.type = type;
        this.output = output;
    }

    public static String asOutput(Piece piece) {
        String output = Arrays.stream(values())
                .filter(pieceOutput -> piece.getClass() == pieceOutput.type)
                .findFirst()
                .orElse(NONE)
                .output;
        return decideByColor(piece, output);
    }

    private static String decideByColor(Piece piece, String output) {
        if (piece.isBlack()) {
            return output.toUpperCase();
        }
        return output;
    }
}
