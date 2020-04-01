package chess.dto;

import chess.domain.game.Turn;

public class TurnDto {
	private Turn turn;

	public TurnDto(Turn turn) {
		this.turn = turn;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}
}
