package chess.view;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.util.ConsoleOutputRenderer;

public class OutputView {
	private OutputView() {
	}

	public static void printStartMessage() {
		System.out.println("> 체스 게임을 시작합니다.\n"
				+ "> 게임 시작 : start\n"
				+ "> 게임 종료 : end\n"
				+ "> 게임 이동 : move [source 위치] [target 위치] - 예) move b2 b3");
	}

	public static void printBoard(Board board) {
		System.out.println(ConsoleOutputRenderer.renderBoard(board));
	}

	public static void printScore(Map<Color, Double> eachScore) {
		eachScore.forEach((a, b) -> System.out.println(a + " : " + b));
	}

	public static void printWinner(Color color) {
		System.out.printf("우승자: %s\n", color.name());
	}

	public static void printErrorMessage(String message) {
		System.err.println(message);
	}
}
