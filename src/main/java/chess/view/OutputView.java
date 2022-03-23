package chess.view;

public class OutputView {

	public static void printStartMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printErrorMessage(final String message) {
		System.out.println(message);
	}
}
