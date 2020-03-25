package chess.controller;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public static void run() {
		OutputView.printGameStartInstruction();
		String decision = InputView.inputGameStartOrEnd();
		Board board = new Board();
		Pieces pieces = board.getPieces();
		OutputView.printChessBoard(pieces);

		while (true) {
			movePiece(board, pieces);
		}

	}

	private static void movePiece(Board board, Pieces pieces) {
		String moveInstruction = InputView.inputMoveInstruction();
		Position source = new Position(moveInstruction.split(" ")[1]);
		Position destination = new Position(moveInstruction.split(" ")[2]);
		board.movePiece(source, destination);
		OutputView.printChessBoard(pieces);
	}
}
