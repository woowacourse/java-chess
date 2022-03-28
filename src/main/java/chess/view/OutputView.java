package chess.view;

import chess.dto.Response;

public class OutputView {

    public void printIntroduction() {
        System.out.println("> 체스 게임을 시작합니다." + System.lineSeparator() +
                "> 게임 시작 : start" + System.lineSeparator() +
                "> 게임 종료 : end" + System.lineSeparator() +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator() +
                "> 중간 점수 : status");
    }

    public void printException(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printEnd() {
        System.out.println("게임이 종료되었습니다.");
    }

    public void printResponse(Response response) {
        System.out.println(response.getInformation());
        System.out.println(response.getMetaInformation());
    }
}
