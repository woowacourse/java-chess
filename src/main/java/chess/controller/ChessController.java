package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;
import java.util.List;

public class ChessController {

	public void run() {
		InputView.printCommandGuide();
		List<String> inputCommand = InputView.requestCommand();

		if (!Command.isStart(inputCommand.get(0))) {
			throw new IllegalArgumentException("게임 시작을 먼저 해야 합니다.");
		}
		Board board = new Board();
		OutputView.printBoard(board);

		inputCommand = InputView.requestCommand();

		while (!Command.isEnd(inputCommand.get(0))) {
			if (Command.isStart(inputCommand.get(0))) {
				throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
			}
			if (Command.isMove(inputCommand.get(0))) {
				Position source = PositionConvertor.to(inputCommand.get(1));
				Position target = PositionConvertor.to(inputCommand.get(2));
				board.move(source, target);
				OutputView.printBoard(board);
			}
			if (Command.isStatus(inputCommand.get(0))) {
				double blackScore = board.calculateScore(Team.BLACK);
				double whiteScore = board.calculateScore(Team.WHITE);
				StatusResult result = new StatusResult(blackScore, whiteScore);
				OutputView.printScore(result);
			}
			inputCommand = InputView.requestCommand();
		}
	}
}
