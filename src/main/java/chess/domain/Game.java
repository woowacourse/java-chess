package chess.domain;

import java.util.List;
import java.util.Objects;

public class Game {
	private final Board board;
	private Piece.Color color;

	private Game(final Board board) {
		this.board = board;
		this.color = Piece.Color.WHITE;
	}

	public static Game from(Board board) {
		return new Game(board);
	}

	public boolean action(Position origin, Position target) {
		if (board.isSameColor(origin, color) && board.action(origin, target)) {
			color = changeColor();
			return true;
		}
		return false;
	}

	private Piece.Color changeColor() {
		return color == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
	}

	public Piece.Color currentColor() {
		return color;
	}

	public double scoreOfColor(final Piece.Color color) {
		ScoreCalculator scoreCalculator = ScoreCalculator.getInstance();
		return scoreCalculator.scoreOfColor(values(), color);
	}

	public List<Square> values() {
		return board.values();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Game game = (Game) o;
		return Objects.equals(board, game.board) &&
				color == game.color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(board, color);
	}
}
