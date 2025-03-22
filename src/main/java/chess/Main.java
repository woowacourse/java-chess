package chess;

import chess.piece.Piece;
import chess.position.Column;
import chess.position.Offset;
import chess.position.Position;
import chess.position.Row;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static Position[][] positionBoard = Initializer.initializePositionBoard();
    public static Piece[][] pieceBoard = Initializer.initializePieceBoard();

    public static void main(String[] args) {
        Output.printBoard();

        while (true) {
            Position before = inputPosition();
            Position after = inputPosition();
            Offset offset = Offset.calculate(before, after);


        }
    }

    public static Position inputPosition() {
        String[] inputs = scanner.nextLine().split("");
        return new Position(
                Column.from(inputs[1]), Row.from(inputs[0])
        );
    }
}
