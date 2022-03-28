package chess.view;

import chess.dto.Response;

public class OutputView {

    public void printIntroduction() {
        System.out.println("체스 게임을 시작합니다.\n" +
            "게임 시작은 start, 종료는 end 명령을 입력하세요.");
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
