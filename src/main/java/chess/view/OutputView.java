package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

	public static void printBoard(final Board board) {
		final Map<Position, Piece> boardInformation = board.getBoard();

		for (Position position : Position.getReversePositions()) {
			System.out.print(boardInformation.get(position).getSymbol());
			printBlank(position);
		}
	}

	private static void printBlank(final Position position) {
		if (position.isEndColumn()) {
			System.out.println();
		}
	}
}
