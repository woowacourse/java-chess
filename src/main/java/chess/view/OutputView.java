package chess.view;

import chess.domain.chessBoard.ChessBoard;
import java.util.List;

public class OutputView {

    private static final int CHESS_BOARD_LENGTH = 8;

    public void printStartGameMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public void printCommandGuideMessage() {
        System.out.println(
                """
                        > 게임 시작 : start
                        > 게임 종료 : end
                        > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printChessBoard(ChessBoard chessBoard) {
        List<String> board = chessBoard.showBoard();

        for (int i = 0; i < CHESS_BOARD_LENGTH; i++) {
            String boardLine = String.join("", board.subList(i * CHESS_BOARD_LENGTH, (i + 1) * CHESS_BOARD_LENGTH));
            System.out.println(boardLine);
        }
        System.out.println();
    }
}
