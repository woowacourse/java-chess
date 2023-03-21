package chess.view;

import chess.controller.ChessBoardDto;
import chess.domain.Command;

public class OutputView {

    public void printGameGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println(String.format("> 게임 시작 : %s", Command.START));
        System.out.println(String.format("> 게임 종료 : %s", Command.END));
        System.out.println(String.format("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
                , Command.MOVE, Command.MOVE));
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        chessBoardDto.getBoard().forEach(System.out::println);
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
