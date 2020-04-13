package chess.view;

import java.util.Map;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printInitialMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작 : start");
		System.out.println("게임 종료 : end");
		System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printBoard(Map<String, String> board) {
		for (int i = 8; i >= 1; i--) {
			for (char c = 'a'; c <= 'h'; c++) {
				String key = String.valueOf(c) + i;
				System.out.print(Team.getPieceName(PieceFactory.of(board.get(key)).create(Position.of(key))));
			}
			System.out.print(NEW_LINE);
		}
		System.out.print(NEW_LINE);
	}

	public static void printScore(Map<String, String> result) {
		System.out.println("백 팀 점수: " + result.get("whiteScore"));
		System.out.println("흑 팀 점수: " + result.get("blackScore"));
		System.out.println("승자: " + result.get("result"));
	}
}
