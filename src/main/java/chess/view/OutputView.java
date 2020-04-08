package chess.view;

import chess.dto.BoardDTO;
import chess.dto.StatusDTO;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printInitialMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작 : start");
		System.out.println("게임 종료 : end");
		System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printBoard(BoardDTO board) {
		for (int i = 8; i >= 1; i--) {
			for (char c = 'a'; c <= 'h'; c++) {
				String position = String.valueOf(c) + i;
				System.out.print(board.getPieceIn(position));
			}
			System.out.print(NEW_LINE);
		}
		System.out.print(NEW_LINE);
	}

	public static void printScore(StatusDTO status) {
		System.out.println("백 팀 점수: " + status.getWhiteScore());
		System.out.println("흑 팀 점수: " + status.getBlackScore());
		System.out.println("승자: " + status.getWinner());
	}
}
