package chess.view;

import chess.domain.chessBoard.ChessBoard;

public class ConsoleOutputView {

	static final String PROMPT = "> ";

	private static void printNewLine() {
		System.out.println();
	}

	public static void printChessStart() {
		System.out.println(PROMPT + "체스 게임을 시작합니다.");
		System.out.println(PROMPT + "게임 시작 : start");
		System.out.println(PROMPT + "게임 종료 : end");
		System.out.println(PROMPT + "게임 이동 : move source위치 target위치 - 예. move b2 b3");
		printNewLine();
	}

	public static void printChessBoard(ChessBoard chessBoard) {
		ChessBoardRenderer.render(chessBoard).forEach(System.out::println);
	}

}
