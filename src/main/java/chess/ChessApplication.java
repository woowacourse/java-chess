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
		start(board);
		play(board);
	}

	private static void start(final Board board) {
		String inputCommand = InputView.askStart();
		operateStart(board, inputCommand);
	}

	private static void operateStart(final Board board, final String inputCommand) {
		try {
			Command.ofStart(inputCommand);
			final Map<Position, Piece> chessBoard = board.board();
			OutputView.printBoard(chessBoard, ranks(), files());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			start(board);
		}

	}

	private static void play(final Board board) {
		String inputCommand = InputView.askNext();
		operatePlay(board, inputCommand);
	}

	private static void operatePlay(final Board board, final String inputCommand) {
		try {
			Command command = Command.ofCommand(inputCommand);
			operateCommand(board, command);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		} finally {
			play(board);
		}

	}

	private static void operateCommand(Board board, Command command) {
		if (command.isEnd()) {
			return;
		}
		if (command.isMove()) {
			movePiece(board, command);
		}
		if (command.isStatus()) {
			// TODO : status관련 로직
		}
	}

	private static void movePiece(Board board, Command command) {
		board.move(command.getSource(), command.getTarget());
		OutputView.printBoard(board.board(), board.ranks(), board.files());
		play(board);
	}
}
