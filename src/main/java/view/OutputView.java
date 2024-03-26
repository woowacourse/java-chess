package view;

import dto.GameBoardDto;
import java.util.Map;
import model.Camp;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printGameBoard(GameBoardDto gameBoardDto) {
        System.out.println(gameBoardDto.getValue());
    }

    public void printException(final Exception exception) {
        System.out.printf("[ERROR] %s%n", exception.getMessage());
    }

    public void printCurrentCame(final Camp camp) {
        System.out.printf("현재 턴: %s%n%n", camp.toString());
    }

    public void printResult(Map<Camp, Double> result) {
        System.out.println(result);
    }

    public void printWinner(Camp winner) {
        System.out.println("우승자는 " + winner + "입니다.");
    }
}
