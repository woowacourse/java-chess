package view;

import domain.board.Board;
import domain.board.Turn;
import domain.piece.Piece;
import java.util.List;

public class OutputView {

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
