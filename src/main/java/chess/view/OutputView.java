package chess.view;

import java.util.List;

import chess.dto.ViewBoardDto;
import chess.dto.TempResultDto;

public class OutputView {

	public static void printInitialMessage() {
		System.out.println(
			"> 체스 게임을 시작합니다.\n"
			+ "> 게임 시작 : start\n"
			+ "> 게임 종료 : end\n"
			+ "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n"
			+ "> 게임 결과 : status"
		);
	}

	public static void printLoadMessage() {
		System.out.println("마지막으로 저장된 게임을 불러옵니다.\n");
	}

	public static void printBoard(ViewBoardDto viewBoardDto) {
		System.out.println(viewBoardDto.getTurn() + "의 차례!");
		viewBoardDto.getBoard()
			.forEach(line -> System.out.println(printLine(line)));
		System.out.println();
	}

	private static String printLine(List<String> line) {
		return String.join("",line);
	}

	public static void printTempResult(TempResultDto tempResultDto) {
		System.out.println("현재 승자: " + tempResultDto.getWinner()
			+ " / 백 점수: " + tempResultDto.getWhiteScore()
			+ " / 흑 점수: " + tempResultDto.getBlackScore() + "\n");
	}

	public static void printWinner(String team) {
		System.out.println(team + "이 최종 승리했습니다!");
	}

	public static void printErrorMessage(String errorMessage) {
		System.out.println(errorMessage);
	}
}
