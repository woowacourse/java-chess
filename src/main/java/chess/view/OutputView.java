package chess.view;

import java.util.List;

public final class OutputView {

    private static final String DELIMITER = "";

    public void startMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printRank(List<String> pieceViews) {
        String format = String.join(DELIMITER, pieceViews);

        System.out.println(format);
    }

    public void printErrorMesage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printGuideMessage() {
        System.out.println("명령어를 다시 입력하세요");
    }
}
