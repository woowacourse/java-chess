package view;

import domain.board.Board;
import domain.piece.Piece;
import java.util.List;

public class OutputView {

    public void printStartNotice() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(Board board) {
        List<Piece> pieces = board.extractPieces();
        for (int i = 0; i < pieces.size(); i++) {
            String piece = pieces.get(i).display();
            System.out.print(piece);
            separateLineByFileIndex(i);
        }
        System.out.println();
    }

    private void separateLineByFileIndex(int fileIndex) {
        if (isLastFile(fileIndex)) {
            System.out.println();
        }
    }

    private boolean isLastFile(int fileIndex) {
        return fileIndex % 8 == 7;
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
