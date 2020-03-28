package chess.contoller;

import chess.contoller.command.UserCommand;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public void run() {
		OutputView.printInitMessage();
		State state = StateFactory.create();
		while (!state.isEnd()) {
			state = actCommand(state);
		}
	}

	private State actCommand(State state) {
		try {
			UserCommand command = UserCommand.of(InputView.inputCommand());
			state = command.execute(state);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return state;
	}
}
