package chess;

import chess.piece.Piece;
import chess.position.Column;
import chess.position.Offset;
import chess.position.Position;
import chess.position.Row;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static Piece[][] pieceBoard = Initializer.initializePieceBoard();

    public static void main(String[] args) {

        while (true) {
            Output.printBoard();

            Position before = inputPosition();
            Position after = inputPosition();
            Offset offset = Offset.calculate(before, after);
            if (offset.is00()) {
                throw new IllegalArgumentException("안움직였어");
            }
            Piece selectPiece = pieceBoard[before.getI()][before.getJ()];
            if (selectPiece == null) {
                throw new IllegalArgumentException("아니 없는걸 선택하면 어떡행..");
            }

            상대기물죽이는기능(after);

            if (!selectPiece.canMove(offset)) {
                throw new IllegalArgumentException("거기로 못가는 기물임");
            }

            pieceBoard[before.getI()][before.getJ()] = null;
            pieceBoard[after.getI()][after.getJ()] = selectPiece;
        }
    }

    private static void 상대기물죽이는기능(final Position after) {
        Piece destinationPiece = pieceBoard[after.getI()][after.getJ()];
        // TODO
    }

    public static Position inputPosition() {
        String[] inputs = scanner.nextLine().split("");
        return new Position(
                Column.from(inputs[0]), Row.from(inputs[1])
        );
    }
}
