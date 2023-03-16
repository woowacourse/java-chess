package chess.view;

import chess.controller.ChessBoardDto;

public class OutputView {

    public void printGameGuide() { // TODO: 2023/03/16 명령어 관리하는 곳에서 상수 or enum으로 정의해서 새로 포매팅
        System.out.println(""
                + "> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        for (String oneFile : chessBoardDto.getBoard()) {
            System.out.println(oneFile);
        }
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
