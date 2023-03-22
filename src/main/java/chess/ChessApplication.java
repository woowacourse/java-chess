package chess;

import java.util.List;
import java.util.Map;

import chess.domain.Board;
import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
		final List<Rank> ranks = board.getRanks();
		final List<File> files = board.getFiles();
		OutputView.printBoard(chessBoard, ranks, files);
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
