package chess;

import java.util.List;

import chess.domain.MoveInfo;
import chess.domain.Turn;
import chess.domain.board.BoardFactory;
import chess.domain.board.Boards;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
	public static void main(String[] args) {
		OutputView.printInitialMessage();
		List<String> initialInputs = List.of("start", "end");
		String initialInput = InputView.inputInitial();

		if (!initialInputs.contains(initialInput)) {
			throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
		}

		if ("start".equals(initialInput)) {
			Boards boards = BoardFactory.create();
			OutputView.printBoard(boards.getTotal());
			Turn turn = Turn.UPPER;
			String moveOrStatus = InputView.inputMoveInfo();
			while (boards.isBothKingAlive()) {
				if ("status".equals(moveOrStatus)) {
					OutputView.printScore(turn.getName(), ChessService.getScore(boards, turn));
					continue;
				}
				ChessService.move(boards, turn = turn.next(), MoveInfo.of(moveOrStatus));
				OutputView.printBoard(boards.getTotal());
				moveOrStatus = InputView.inputMoveInfo();
			}
		}
	}
}
