package chess.view;

import chess.board.Board;
import chess.piece.LivePiece;

public class OutputView {

    public void printBoard(Board board) {
        // 0과 9는 index 정보 주기

        char[][] arr = createCharArrayWithBorder();
        printCharArray(arr);
    }

    private void printCharArray(char[][] arr) {
        for (int i = 9; i >= 0; --i) {
            for (int j = 0; j < 10; ++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    // 테두리 문자열을 넣은 char[][] 반환
    private char[][] createCharArrayWithBorder() {
        char[][] arr = createEmptyCharArray();

        for (int i = 1; i <= 8; ++i) {
            arr[i][0] = arr[i][9] = (char) ('0' + i);
        }

        for (int j = 1; j <= 8; ++j) {
            arr[0][j] = arr[9][j] = (char) ('A' + j - 1);
        }
        return arr;
    }

    private char[][] createEmptyCharArray() {
        char[][] arr = new char[10][10];
        for (int i = 9; i >= 0; --i) {
            for (int j = 0; j < 10; ++j) {
                arr[i][j] = ' ';
            }
        }
        return arr;
    }
}
