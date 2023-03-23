package chess;

import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

import java.util.Map;

import chess.domain.Board;
import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

	public static void main(String[] args) {
		Board board = Board.create();
		try {
			start(board);
			play(board);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			play(board);
		}
	}

	private static void start(final Board board) {
		String inputCommand = InputView.askStart();
		Command.ofStart(inputCommand);

		final Map<Position, Piece> chessBoard = board.getBoard();
		OutputView.printBoard(chessBoard, getRanks(), getFiles());
	}

	private static void play(final Board board) {
		String inputCommand = InputView.askNext();
		Command command = Command.ofMoveOrEnd(inputCommand);
		if (command.isEnd()) {
			return;
		}
		if (command.isMove()) {
			movePiece(board, command);
		}
	}

	private static void movePiece(Board board, Command command) {
		board.move(command.getSource(), command.getTarget());
		OutputView.printBoard(board.getBoard(), board.getRanks(), board.getFiles());
		play(board);
	}
}
