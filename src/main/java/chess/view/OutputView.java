package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.Command;

public class OutputView {

    public void printGameGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println(String.format("> 게임 종료 : %s", Command.END.getValue()));
        System.out.println(String.format("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
            , Command.MOVE.getValue(), Command.MOVE.getValue()));
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
