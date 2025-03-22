package chess;

import chess.piece.Piece;
import java.util.Scanner;

public class ChessGame {

    private final Board board = new Board();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        Color turn = Color.WHITE;
        while (true) {
            if (turn.isWhite()) {
                System.out.print("WHITE ");
            }

            if (turn.isBlack()) {
                System.out.print("BLACK ");
            }
            System.out.println("Turn.");

            System.out.println("Select Piece Position And Destination Position (ex. A2 A4)");
            String moveXY = scanner.nextLine();

            String[] split = moveXY.split(" ");

            Position start = new Position(Row.change(split[0].charAt(1)), Column.change(split[0].charAt(0)));
            Position end = new Position(Row.change(split[1].charAt(1)), Column.change(split[1].charAt(0)));

            if (!board.existPieceIn(start)) {
                throw new IllegalArgumentException("[ERROR] No Piece");
            }

            Piece piece = board.getPieceIn(turn, start);
            piece.move(end);

            if (turn.isWhite()) {
                System.out.print("WHITE ");
            }

            if (turn.isBlack()) {
                System.out.print("BLACK ");
            }
            System.out.printf("%s move: %s to %s%n", piece.name(), start, end);
            turn = turn.opposite();
        }
    }
}
