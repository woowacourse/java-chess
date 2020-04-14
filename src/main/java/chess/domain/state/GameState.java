package chess.domain.state;

import static chess.domain.piece.Team.*;

import java.util.Objects;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;

public abstract class GameState {
	protected final int id;
	protected final Board board;
	protected final StateType stateType;
	protected Team turn;

	public GameState(Board board, StateType stateType) {
		this(1, board, stateType, WHITE);
	}

	public GameState(Board board, StateType stateType, Team turn) {
		this(1, board, stateType, turn);
	}

	public GameState(int id, Board board, StateType stateType, Team turn) {
		this.id = id;
		this.board = Objects.requireNonNull(board);
		this.stateType = Objects.requireNonNull(stateType);
		this.turn = Objects.requireNonNull(turn);
	}

	public abstract GameState start();

	public abstract GameState move(Position from, Position to);

	public abstract Result status();

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

	public String getStateType() {
		return stateType.getState();
	}

	public int getId() {
		return id;
	}
}
