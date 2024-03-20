package view;

import domain.board.Board;
import domain.piece.Piece;
import domain.piece.Type;
import java.util.Arrays;
import java.util.List;

public class OutputView {

    public void printInitBoard(Board board) {
        List<Piece> pieces = board.extractPieces();
        for (int i = 0; i < pieces.size(); i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            String piece = PieceOutput.asString(pieces.get(i));
            System.out.print(piece);
        }
    }

    private enum PieceOutput {

        BISHOP(Type.BISHOP, "B"),
        KING(Type.KING, "K"),
        KNIGHT(Type.KNIGHT, "N"),
        PAWN(Type.PAWN, "P"),
        QUEEN(Type.QUEEN, "Q"),
        ROOK(Type.ROOK, "R"),
        NONE(Type.NONE, ".");

        private final Type type;
        private final String output;

        PieceOutput(Type type, String output) {
            this.type = type;
            this.output = output;
        }

        private static String asString(Piece piece) {
            String output = Arrays.stream(values())
                    .filter(pieceOutput -> piece.isSameType(pieceOutput.type))
                    .findFirst()
                    .orElse(NONE)
                    .output;
            if (piece.isWhite()) {
                return output.toLowerCase();
            }
            return output;
        }
    }
}
