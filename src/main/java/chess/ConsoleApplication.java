package chess;

import java.util.Arrays;
import java.util.List;

public class ConsoleApplication {

    public static void main(final String[] args) {
        final BoardInitializer initializer = new BoardInitializer();
        final Board board = new Board(initializer.initialize());
        final List<Piece> pieces = board.getPieces();

        String[][] boards = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boards[i][j] = ".";
            }
        }

        for (Piece piece : pieces) {
            final Position position = piece.getPosition();
            final PieceType pieceType = piece.getPieceType();
            boards[position.getRow()][position.getColumn()] = pieceType.getName().name();
        }

        for (String[] strings : boards) {
            System.out.println(Arrays.toString(strings));
        }
    }

}
