package chess.controller;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	private State state = State.BEFORESTART;

	public void run() {
		Board board = new Board();
		Pieces pieces = board.getPieces();

		OutputView.printGameStartInstruction();
		state = State.of(InputView.inputGameStartOrEnd());

		while (state == State.RUNNING && pieces.isBothKingAlive()) {
			OutputView.printChessBoard(pieces);
			processCommand(board);
		}
		if (!pieces.isBothKingAlive()) {
			OutputView.printWinner(board.getWinner());
		}
	}

	private void processCommand(Board board) {
		try {
			String decision = InputView.inputInstruction();
			if (decision.contains("move")) {
				Position source = new Position(decision.split(" ")[1]);
				Position destination = new Position(decision.split(" ")[2]);
				board.movePiece(source, destination);
				return;
			}
			if (decision.equals("status")) {
				OutputView.printScore(board);
				OutputView.printTeamWithHigherScore(board);
				return;
			}
			if (decision.equals("end")) {
				state = State.FINISHED;
				return;
			}
			throw new IllegalArgumentException("올바른 명령을 입력해 주십시오.");
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}
}
