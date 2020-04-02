package chess.domain.state;

import java.util.List;
import java.util.function.UnaryOperator;

public enum Command {
	START(ChessGameState::start),
	END(ChessGameState::end),
	MOVE(null);

	UnaryOperator<ChessGameState> operator;

	Command(UnaryOperator<ChessGameState> operator) {
		this.operator = operator;
	}

	public boolean isSameCommand(String userInput) {
		return this.toString().equals(userInput.toUpperCase());
	}

	public ChessGameState run(ChessGameState state) {
		return operator.apply(state);
	}

	public ChessGameState move(ChessGameState state, List<String> parameters) {
		return state.move(parameters);
	}
}
