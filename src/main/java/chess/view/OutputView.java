package chess.view;

import chess.domain.chessBoard.Space;
import java.util.List;

public class OutputView {

    private static final int CHESS_BOARD_WIDTH = 8;

    public void printStartGameMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public void printCommandGuideMessage() {
        System.out.println(
                "> 게임 시작 : start\n"
                        + "> 게임 종료 : end\n"
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(List<Space> spaces) {
        int count = 0;
        for (Space space : spaces) {
            System.out.print(PieceSign.findSign(space.getPiece()));
            count++;
            if (count == CHESS_BOARD_WIDTH) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }
}
