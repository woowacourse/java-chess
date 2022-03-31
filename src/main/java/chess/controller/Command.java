package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.score.ScoreResult;
import chess.domain.state.State;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public enum Command {

	START("start", (state, input) -> {
		Board board = new Board(BoardFactory.initiate());
		return state.start(board);
	}),
	END("end", (state, input) -> state.finish()),
	MOVE("move", (state, input) -> {
		Position source = PositionConvertor.to(input.get(1));
		Position target = PositionConvertor.to(input.get(2));
		return state.play(source, target);
	}),
	STATUS("status", (state, input) -> {
		ScoreResult result = state.createStatus();
		OutputView.printScore(result);
		return state;
	});

	private static final String NOT_FOUND_ERROR = "해당 명령어를 찾지 못했습니다.";
	private static final int COMMAND_INDEX = 0;

	private final String command;
	private final BiFunction<State, List<String>, State> function;

	Command(final String command, final BiFunction<State, List<String>, State> function) {
		this.command = command;
		this.function = function;
	}

	public static Command of(final List<String> requests) {
		return Arrays.stream(values())
				.filter(value -> value.command.equalsIgnoreCase(requests.get(COMMAND_INDEX)))
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
	}

	public State apply(State state, List<String> input) {
		return function.apply(state, input);
	}
}
