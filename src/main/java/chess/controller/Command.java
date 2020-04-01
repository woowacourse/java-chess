package chess.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.State;
import chess.view.OutputView;

public enum Command {
	MOVE("move ([a-h][1-8]) ([a-h][1-8])", Command::move),
	STATUS("status", Command::showStatus),
	END("end", Command::endGame);

	public static final String DELIMITER = " ";
	public static final int MOVE_COMMAND_ARGUMENT_NUMBER = 3;
	private String name;
	private BiFunction<Board, String, State> execution;

	Command(String name, BiFunction<Board, String, State> execution) {
		this.name = name;
		this.execution = execution;
	}

	public static Command of(String input) {
		return Arrays.stream(Command.values())
			.filter(value -> input.matches(value.name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("올바른 명령이 아닙니다."));
	}

	private static State endGame(Board board, String input) {
		return State.FINISHED;
	}

	private static State showStatus(Board board, String input) {
		OutputView.printScore(board);
		OutputView.printTeamWithHigherScore(board);
		return State.RUNNING;
	}

	private static State move(Board board, String input) {
		List<String> multiArguments = Arrays.asList(input.split(DELIMITER));
		validateMultiArguments(multiArguments);
		Position source = new Position(multiArguments.get(1));
		Position destination = new Position(multiArguments.get(2));
		board.movePiece(source, destination);
		return State.RUNNING;
	}

	private static void validateMultiArguments(List<String> multiArguments) {
		if (multiArguments.size() != MOVE_COMMAND_ARGUMENT_NUMBER) {
			throw new IllegalArgumentException("올바른 좌표값을 입력해 주세요.");
		}
	}

	public State execute(Board board, String input) {
		return execution.apply(board, input);
	}
}
