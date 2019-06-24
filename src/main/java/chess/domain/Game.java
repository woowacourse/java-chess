package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
	private final Board board;
	private Piece.Color currentColor;

	private Game(final Board board) {
		this.board = board;
		this.currentColor = Piece.Color.WHITE;
	}

	public static Game from(Board board) {
		return new Game(board);
	}

	public boolean action(Position origin, Position target) {
		if (board.isSameColor(origin, currentColor) && board.action(origin, target)) {
			currentColor = changeColor();
			return true;
		}
		return false;
	}

	private Piece.Color changeColor() {
		return currentColor == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
	}

	public Piece.Color currentColor() {
		return currentColor;
	}

	public double scoreOfColor(final Piece.Color color) {
		ScoreCalculator scoreCalculator = ScoreCalculator.getInstance();
		return scoreCalculator.scoreOfColor(values(), color);
	}

	public List<Square> values() {
		return new ArrayList<>(board.values());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Game game = (Game) o;
		return Objects.equals(board, game.board) &&
				currentColor == game.currentColor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(board, currentColor);
	}
}
