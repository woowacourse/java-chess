package chess.view;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class OutputView {
	public static void printGameInstruction() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작: start");
		System.out.println("게임 종료: end");
		System.out.println("게임 이동: move source위치 target위치 - 예. move b2 b3");
	}

	public static void printException(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printGameEnd() {
		System.out.println("게임을 종료합니다.");
	}
}
