package chess.domain.state;

import static chess.domain.piece.Team.*;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public abstract class GameState {
	protected final Board board;
	protected final StateType stateType;
	protected Team turn;

	public GameState(Board board, StateType stateType) {
		this(board, stateType, WHITE);
	}

	public GameState(Board board, StateType stateType, Team turn) {
		this.board = board;
		this.stateType = stateType;
		this.turn = turn;
	}

	public abstract GameState start();

	public abstract GameState move(Position from, Position to);

	public abstract Map<Team, Double> status();

	public abstract GameState end();

	public abstract boolean isNotFinished();

	public Team getWinner() {
		throw new UnsupportedOperationException("게임이 끝났을때만 승자를 구할수 있어요.");
	}

	public Board getBoard() {
		return board;
	}

	public Team getTurn() {
		return turn;
	}

	public StateType getStateType() {
		return stateType;
	}
}
