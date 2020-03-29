package chess.contoller.command;

import chess.domain.ChessStatus;
import chess.domain.Side;
import chess.domain.dto.ChessBoardAssembler;
import chess.domain.position.Position;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;

public enum Command {
	START(state -> OutputView.printBoard(ChessBoardAssembler.create(state))) {
		@Override
		public State execute(State state, Queue<String> options) {
			return StateFactory.create();
		}
	},
	MOVE(state -> OutputView.printBoard(ChessBoardAssembler.create(state))) {
		@Override
		public State execute(State state, Queue<String> options) {
			if (Objects.isNull(options) || options.size() < MOVE_OPTION_COUNT) {
				throw new IllegalArgumentException("필요한 옵션이 없습니다.");
			}
			return state.move(new Position(options.poll()), new Position(options.poll()));
		}
	},
	END(state -> {
	}) {
		@Override
		public State execute(State state, Queue<String> options) {
			return state.end();
		}
	},
	STATUS(state -> {
		ChessStatus chessStatus = state.createStatus();
		OutputView.printScore(chessStatus.calculateScore(Side.BLACK), chessStatus.calculateScore(Side.WHITE));
	}) {
		@Override
		public State execute(State state, Queue<String> options) {
			return state;
		}
	};

	private static final int MOVE_OPTION_COUNT = 2;

	private final Consumer<State> chess;

	Command(Consumer<State> chess) {
		this.chess = chess;
	}

	public static Command of(String string) {
		return Arrays.stream(values())
				.filter(command -> command.isMatch(string))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("일치하는 실행문이 없습니다."));
	}

	private boolean isMatch(String string) {
		return toString().equalsIgnoreCase(string);
	}

	public void printResult(State state) {
		chess.accept(state);
	}

	public abstract State execute(State state, Queue<String> options);
}
