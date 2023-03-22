package chess.view;

import java.util.Map;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class OutputView {

	public static void printBoard(final Board board) {
		final Map<Position, Piece> chessBoard = board.getBoard();

		for (final Rank rank : board.getRanks()) {
			for (final File file : board.getFiles()) {
				final Position position = Position.of(file, rank);
				System.out.print(chessBoard.get(position).name());
			}
			System.out.println();
		}
	}

	public static void printErrorMessage(String errorMessage) {
		System.out.println("[ERROR] " + errorMessage);
	}
}
