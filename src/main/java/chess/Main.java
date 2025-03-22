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

            boolean killFlag = false;
            Piece targetPiece = pieceBoard[after.getI()][after.getJ()];
            if (targetPiece != null) {
                if (targetPiece.getColor() == selectPiece.getColor()) {
                    throw new IllegalArgumentException("같은 팀이 있는 곳으로 이동할 수 없어. 팀킬이라도 하게?");
                }
                killFlag = true;
            }

            if (!selectPiece.canMove(offset, killFlag)) {
                throw new IllegalArgumentException("거기로 못가는 기물임");
            }

            pieceBoard[before.getI()][before.getJ()] = null;
            pieceBoard[after.getI()][after.getJ()] = selectPiece;
        }
    }

    public static Position inputPosition() {
        String[] inputs = scanner.nextLine().split("");
        return new Position(
                Column.from(inputs[0]), Row.from(inputs[1])
        );
    }
}
