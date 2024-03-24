package view;

import domain.board.Board;
import domain.board.Turn;
import domain.piece.Piece;
import java.util.List;

public class OutputView {

    public void printStartNotice() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Board board) {
        List<Piece> pieces = board.extractPiecesByOrder();
        for (int i = 0; i < pieces.size(); i++) {
            String piece = pieces.get(i).display();
            System.out.print(piece);
            separateLineByFileIndex(i);
        }
        printNewLine();
    }

    private void separateLineByFileIndex(int fileIndex) {
        if (isLastFile(fileIndex)) {
            printNewLine();
        }
    }

    private boolean isLastFile(int fileIndex) {
        return fileIndex % 8 == 7;
    }

    private void printNewLine() {
        System.out.println();
    }

    public void printTurn(Turn turn) {
        if (turn.isBlack()) {
            System.out.println("블랙(대문자) 진영의 차례입니다.");
        }
        if (turn.isWhite()) {
            System.out.println("화이트(소문자) 진영의 차례입니다.");
        }
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
