package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;
import java.util.Map;

public class OutputView {

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
		System.out.println("Black 팀: " + result.getBlackScore());
		System.out.println("White 팀: " + result.getWhiteScore());
		if (result.getWinner() == Team.NEUTRALITY) {
			System.out.println("무승부 입니다.");
			return;
		}
		System.out.println("승리 팀은" + result.getWinner() + "입니다.");
	}
}
