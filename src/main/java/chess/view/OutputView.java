package chess.view;

import chess.domain.dto.ChessBoardDto;

import java.util.List;

public class OutputView {
	public static void printInitMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작 : start");
		System.out.println("게임 종료 : end");
		System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printScore(double blackScore, double whiteScore) {
		System.out.println("총 점수");
		System.out.println("흑 : " + blackScore);
		System.out.println("백 : " + whiteScore);
	}

	public static void printBoard(ChessBoardDto chessBoardDto) {
		for (List<String> strings : chessBoardDto.getChessboard()) {
			printRow(strings);
		}
	}

	private static void printRow(List<String> strings) {
		for (String string : strings) {
			System.out.print(string);
		}
		System.out.println();
	}
}
