package chess;

import static chess.domain.ChessGame.*;
import static chess.domain.command.Command.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static chess.view.InputView.*;
import static chess.view.OutputView.*;

import java.util.Map;

import chess.domain.Board;
import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessApplication {

	public static void main(String[] args) {
		Board board = board();
		start(board);
		play(board);
	}

	private static void start(final Board board) {
		String inputCommand = askStart();
		operateStart(board, inputCommand);
	}

	private static void operateStart(final Board board, final String inputCommand) {
		try {
			ofStart(inputCommand);
			final Map<Position, Piece> chessBoard = board.board();

			printBoard(chessBoard, ranks(), files());
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			start(board);
		}
	}

	private static void play(final Board board) {
		String inputCommand = askNext();
		operatePlay(board, inputCommand);
	}

	private static void operatePlay(final Board board, final String inputCommand) {
		try {
			Command command = ofCommand(inputCommand);
			operateCommand(board, command);
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			play(board);
		}
	}

	private static void operateCommand(Board board, Command command) {
		if (command.isEnd()) {
			return;
		}
		if (command.isMove()) {
			Position source = Position.from(command.source());
			Position target = Position.from(command.target());
			movePiece(board, source, target);
		}
		if (command.isStatus()) {
			printStatus(board.blackStatus(), board.whiteStatus());
			play(board);
		}
	}

	private static void movePiece(final Board board, final Position source, final Position target) {
		final Piece candidateKing = board.move(source, target);
		if (board.isKing(candidateKing)) {
			printKingDead();
			initializeBoard();
			return;
		}
		update();
		printBoard(board.board(), ranks(), files());
		play(board);
	}
}
