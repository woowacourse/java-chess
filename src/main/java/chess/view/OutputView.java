package chess.view;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class OutputView {

	private static final String GAME_INTRO_MESSAGE = "> 체스 게임을 시작합니다.";
	private static final String START_COMMAND_INFORMATION_MESSAGE = "> 게임 시작 : start";
	private static final String END_COMMAND_INFORMATION_MESSAGE = "> 게임 종료 : end";
	private static final String MOVE_COMMAND_INFORMATION_MESSAGE = "> 말 이동 : move source위치 target위치 - 예. move b2 b3";
	private static final String NEW_LINE = System.lineSeparator();

	private OutputView() {
	}

	public static void printGameIntro() {
		System.out.println(GAME_INTRO_MESSAGE);
		System.out.println(START_COMMAND_INFORMATION_MESSAGE);
		System.out.println(END_COMMAND_INFORMATION_MESSAGE);
		System.out.println(MOVE_COMMAND_INFORMATION_MESSAGE);
	}

	public static void printBoard(Board board) {
		StringBuilder builder = new StringBuilder();

		for (int rank = 8; rank >= 1; rank--) {
			for (int file = 1; file <= 8; file++) {
				Piece piece = board.findPiece(Position.of(file, rank));
				builder.append(findSymbol(piece));
			}
			builder.append(NEW_LINE);
		}
		System.out.println(builder);
	}

	private static String findSymbol(Piece piece) {
		return piece.getSymbol();
	}

	public static void printStatus(Map<Team, Double> status) {
		System.out.println(status);
	}

	public static void printWinner(Team winner) {
		System.out.printf("승리 : %s\n", findWinner(winner));
	}

	private static String findWinner(Team winner) {
		if (winner.isBlack()) {
			return "흑팀";
		}
		if (winner.isWhite()) {
			return "백팀";
		}
		return "없음";
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
