package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

	protected static final String MOVEMENT_ERROR = "해당 기물은 그곳으로 이동할 수 없습니다.";

	private final String symbol;
	protected final Team team;

	public Piece(final Team team) {
		this.symbol = createSymbol(team);
		this.team = team;
	}

	protected abstract String createSymbol(final Team team);

	public abstract void validateMovement(Position source, Position target);

	public abstract boolean isBlank();

	public String getSymbol() {
		return symbol;
	}
}
