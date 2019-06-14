package chess.view;

public class OutputVIew {

    public static void printInputGuide() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoardState() {
        System.out.println("RNBQKBNR");
        System.out.println("PPPPPPPP");
        System.out.println("........");
        System.out.println("........");
        System.out.println("........");
        System.out.println("........");
        System.out.println("pppppppp");
        System.out.println("rnbqkbnr");
    }

}
