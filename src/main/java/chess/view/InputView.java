package chess.view;

public class InputView {
    public static void printStart() {
        System.out.println(String.join("\n",
                "> 체스 게임을 시작합니다.",
                "> 게임 시작 : start",
                "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"));
    }

    public static void printMoveRequest() {
        System.out.println("움직여주세요.");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printResultRequest() {
        System.out.println("결과가 끝났습니다. 결과를 보시겠습니까?");
    }
}
