package chess;

import chess.piece.BlankPiece;
import chess.piece.Piece;

import java.util.Map;
import java.util.Scanner;

public class Console {

    private final Scanner scanner = new Scanner(System.in);

    public String readPositions() {
        System.out.println("시작점과 끝점 입력 (예: A1,C3)");
        return scanner.nextLine();
    }

    public void printPieces(Map<Position, Piece> pieces) {
        for (Row row : Row.values()) {
            for (Column col : Column.values()) {
                System.out.print(pieces.getOrDefault(new Position(col, row), new BlankPiece(Color.EMPTY)) + " ");
            }
            System.out.println();
        }
    }

    public void printSide(Color currentTurn) {
        if (currentTurn.isBlack()) {
            System.out.println("블랙의 차례입니다.");
        }
        if (currentTurn.isWhite()) {
            System.out.println("화이트의 차례입니다.");
        }
    }
}
