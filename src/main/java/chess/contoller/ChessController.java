package chess.contoller;

import java.util.List;

import chess.domain.Side;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public void run() {
		OutputView.printInitMessage();
		State state = StateFactory.create(InputView.inputCommand().get(0));

		while (!state.isEnd()) {
			OutputView.createEmptyBoard(Column.values().length, Row.values().length);
			OutputView.printInitBoard(state.getChessBoard());
			List<String> command = InputView.inputCommand();
			if (command.get(0).equals("end")) {
				state = state.end();
			} else if (command.get(0).equals("move")) {
				state = state.move(new Position(command.get(1)), new Position(command.get(2)));
			} else if (command.get(0).equals("status")) {
				OutputView.printScore(state.status(Side.BLACK), state.status(Side.WHITE));
			} else {
				throw new IllegalArgumentException("잘못된 명령입니다.");
			}
		}
	}
}
