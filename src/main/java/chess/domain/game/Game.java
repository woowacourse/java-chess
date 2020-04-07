package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.StateType;

public class Game {
	private GameState state;

	public Game(GameState state) {
		this.state = state;
	}

	public void start() {
		this.state = state.start();
	}

	public void move(Position from, Position to) {
		this.state = state.move(from, to);
	}

	public Map<Team, Double> status() {
		return state.status();
	}

	public void end() {
		this.state = state.end();
	}

	public Board getBoard() {
		return state.getBoard();
	}

	public Team getTurn() {return state.getTurn();}

	public StateType getStateType() {return state.getStateType();}

	public Team findWinner() {
		return state.getWinner();
	}

	public boolean isNotEnd() {
		return state.isNotFinished();
	}

	public int getId() {
		return state.getId();
	}
}
