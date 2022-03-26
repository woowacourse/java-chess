package chess;

import java.util.List;
import java.util.NoSuchElementException;

import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
import chess.domain.command.Start;
import chess.domain.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
		outputView.displayGameRule();
		State state = State.create();
		while (!state.isFinished()) {
			state = processOneTurn(state);
		}
	}

	private State processOneTurn(State state) {
		try {
			Command command = convertCommand(inputView.askCommand());
			state = state.proceed(command);
			outputView.displayChessBoard(state.getBoard());
		} catch (IllegalArgumentException | IllegalStateException
			| NoSuchElementException exception){
			System.out.println(exception.getMessage());
		}
		return state;
	}

	private Command convertCommand(List<String> input) {
		if (input.get(0).equals("start")) {
			return new Start();
		}
		if (input.get(0).equals("end")) {
			return new End();
		}
		return new Move(input.get(1), input.get(2));
	}
}
