package chess.domain.piece;

import java.util.Map;
import java.util.Objects;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;

public abstract class Piece {

	protected final Symbol symbol;
	protected final Team team;

	protected Piece(final Symbol symbol, final Team team) {
		this.symbol = symbol;
		this.team = team;
	}

	public boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to) {
		Piece toPiece = value.get(to);
		if (isSameTeam(toPiece.team) || !hasDirection(Direction.of(from, to))) {
			return false;
		}
		return moveStrategy().isMovable(value, from, to);
	}

	public abstract boolean hasDirection(Direction direction);

	public abstract MoveStrategy moveStrategy();

	public boolean isSameTeam(Team team) {
		return this.team.isSame(team);
	}

	public String getSymbol() {
		if (team.equals(Team.BLACK)) {
			return symbol.getBlack();
		}
		return symbol.getWhite();
	}

	public Team getTeam() {
		return team;
	}

	public boolean isKing() {
		return symbol == Symbol.KING;
	}

	public boolean isPawn() {
		return symbol == Symbol.PAWN;
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return symbol == piece.symbol && team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, team);
	}

	public boolean isBlack() {
		return team.isSame(Team.BLACK);
	}

	public boolean isWhite() {
		return team.isSame(Team.WHITE);
	}

	public double getScore() {
		return symbol.getScore();
	}
}
