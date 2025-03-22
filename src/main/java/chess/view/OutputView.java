package chess.view;

import chess.position.Color;
import chess.board.Board;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import java.util.List;

public class OutputView {

    public static void printBoard(Board board) {
        String[][] boardOutput = new String[8][8];
        List<Piece> pieces = board.getPieces();
        for (int i = 0; i < boardOutput.length; i++) {
            for (int j = 0; j < boardOutput[i].length; j++) {
                boardOutput[i][j] = ".";
            }
        }
        for (Piece piece : pieces) {
            int row = piece.getPosition().row().ordinal();
            int column = piece.getPosition().column().ordinal();
            boardOutput[row][column] = PieceOutput.getPieceOutputByPieceAndColor(piece);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < boardOutput.length; j++) {
                System.out.printf("%s\t", boardOutput[i][j]);
            }
            System.out.printf("\t%d%n", 8 - i);
        }
        System.out.println();
        System.out.println("A\tB\tC\tD\tE\tF\tG\tH");
    }

    enum PieceOutput {
        BISHOP("B"),
        KING("K"),
        KNIGHT("N"),
        PAWN("P"),
        QUEEN("Q"),
        ROOK("R");

        private final String output;

        PieceOutput(String output) {
            this.output = output;
        }

        private static String getPieceOutputByPieceAndColor(Piece piece) {
            if (piece.getColor() == Color.BLACK) {
                return getPieceOutputByPiece(piece).toLowerCase();
            }
            return getPieceOutputByPiece(piece);
        }

        private static String getPieceOutputByPiece(Piece piece) {
            if (piece instanceof Bishop) {
                return BISHOP.output;
            }
            if (piece instanceof King) {
                return KING.output;
            }
            if (piece instanceof Knight) {
                return KNIGHT.output;
            }
            if (piece instanceof Pawn) {
                return PAWN.output;
            }
            if (piece instanceof Queen) {
                return QUEEN.output;
            }
            return ROOK.output;
        }
    }
}
