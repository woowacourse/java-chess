package chess.view;

import java.util.List;

public class ConsoleOutputView {

	static final String PROMPT = "> ";

	private static void printNewLine() {
		System.out.println();
	}

	public static void printChessStart() {
		System.out.println(PROMPT + "체스 게임을 시작합니다.");
		System.out.println(PROMPT + "게임 시작 : start");
		System.out.println(PROMPT + "게임 이동 : move source위치 target위치 - 예. move b2 b3");
		System.out.println(PROMPT + "게임 점수 : status 체스색깔 - 예. status w, status b");
		System.out.println(PROMPT + "게임 종료 : end");
	}

	public static void printChessBoard(List<String> renderedChessBoard) {
		printNewLine();
		renderedChessBoard.forEach(System.out::println);
	}

	public static void printKingCaught(String catchingPieceColor) {
		System.out.println(String.format("%s가 킹을 잡았습니다.", catchingPieceColor));
	}

	public static void printChessEnd() {
		System.out.println(PROMPT + "게임 종료!");
	}

}
