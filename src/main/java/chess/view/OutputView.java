package chess.view;

import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class OutputView {

	public static void printBoard(final Map<Position, Piece> chessBoard, final List<Rank> ranks,
		final List<File> files) {
		for (final Rank rank : ranks) {
			for (final File file : files) {
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
