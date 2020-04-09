package chess.domain.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.domain.state.GameState;

public class Game {
	private static final String ILLEGAL_STATE_CHANGE_REQUEST_EXCEPTION_MESSAGE = "유효하지 않는 변경 요청입니다.";
	private static final Map<String, Consumer<Game>> CHANGE_STATE_FUNCTIONS;
	private static final String START_REQUEST = "start";
	private static final String END_REQUEST = "end";

	static {
		CHANGE_STATE_FUNCTIONS = new HashMap<>();
		CHANGE_STATE_FUNCTIONS.put(START_REQUEST, Game::start);
		CHANGE_STATE_FUNCTIONS.put(END_REQUEST, Game::end);
	}

	private GameState state;

	public Game(GameState state) {
		this.state = Objects.requireNonNull(state);
	}

	public void changeState(String request) {
		if (!CHANGE_STATE_FUNCTIONS.containsKey(request)) {
			throw new IllegalArgumentException(ILLEGAL_STATE_CHANGE_REQUEST_EXCEPTION_MESSAGE);
		}
		Consumer<Game> gameConsumer = CHANGE_STATE_FUNCTIONS.get(request);
		gameConsumer.accept(this);
	}

	public void start() {
		this.state = state.start();
	}

	public void end() {
		this.state = state.end();
	}

	public void move(Position from, Position to) {
		this.state = state.move(from, to);
	}

	public Result status() {
		return state.status();
	}

	public Board getBoard() {
		return state.getBoard();
	}

	public Team getTurn() {
		return state.getTurn();
	}

	public String getStateType() {
		return state.getStateType();
	}

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
