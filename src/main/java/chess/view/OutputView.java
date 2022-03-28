package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;
import java.util.Map;

public class OutputView {

	private static final String ERROR_MESSAGE = "[ERROR] ";
	private static final String BLACK_TEAM = "Black 팀";
	private static final String WHITE_TEAM = "White 팀";
	private static final String WIN_MESSAGE = "이 승리했습니다.";
	private static final String CATCH_KING = "KING을 잡아서 ";
	private static final String END_GAME_WITH_DRAW = "승부가 나지 않고 게임이 종료 되었습니다.";
	private static final String WINNING_MESSAGE = "이 이기고 있습니다.";
	private static final String DELIMITER = ": ";
	private static final String DRAW_MESSAGE = "무승부 입니다.";

	public static void printBoard(final Board board) {
		final Map<Position, Piece> boardInformation = board.getBoard();
		for (Position position : Position.getReversePositions()) {
			System.out.print(boardInformation.get(position).getSymbol());
			printBlank(position);
		}
		System.out.println();
	}

	private static void printBlank(final Position position) {
		if (position.isEndColumn()) {
			System.out.println();
		}
	}

	public static void printScore(final StatusResult result) {
		System.out.println(BLACK_TEAM + DELIMITER + result.getBlackScore());
		System.out.println(WHITE_TEAM + DELIMITER + result.getWhiteScore());
		if (result.getWinner() == Team.NEUTRALITY) {
			System.out.println(DRAW_MESSAGE);
			return;
		}
		printScoreWinner(result.getWinner());
	}

	public static void printScoreWinner(final Team winner) {
		if (winner.isBlack()) {
			System.out.println(BLACK_TEAM + WINNING_MESSAGE);
		}
		System.out.println(WHITE_TEAM + WINNING_MESSAGE);
	}

	public static void printWinner(final Team winner) {
		if (winner.isBlack()) {
			System.out.println(CATCH_KING + BLACK_TEAM + WIN_MESSAGE);
		}
		if (winner.isWhite()) {
			System.out.println(CATCH_KING + WHITE_TEAM + WIN_MESSAGE);
		}
		System.out.println(END_GAME_WITH_DRAW);
	}

	public static void printError(final String message) {
		System.out.println(ERROR_MESSAGE + message);
	}
}
