package chess.view;

import chess.domain.Board;

public class OutputView {

    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final Board board) {
        System.out.println(BoardView.showChessBoard(board));
        System.out.println();
    }

    public void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
