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
			UserCommand command = UserCommand.of(InputView.inputCommand());
			state = command.execute(state);
		}
	}
}
