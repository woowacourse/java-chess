package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private static final String START_COMMAND = "start";
    private static final int STARTING_VALUE_OF_FILE = 1;
    private static final char STARTING_CHARACTER_OF_FILE = 'a';
    private static final char STARTING_VALUE_OF_RANK = '0';

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();
        final Board board = new Board(boardFactory);

        final String command = InputView.readStartCommand();

        if (canNotStart(command)) {
            return;
        }

        final Color turn = Color.WHITE;
        startGame(board, turn);
    }

    private void startGame(final Board board, Color turn) {

        while (true) {
            OutputView.printBoard(board.chessBoard());

            final List<String> moveCommand = InputView.readMoveCommand();
            final Position fromPosition = convertPositionFrom(moveCommand.get(0));
            final Position toPosition = convertPositionFrom(moveCommand.get(1));

            board.move(fromPosition, toPosition, turn);

            turn = turn.opposite();
        }
    }

    private Position convertPositionFrom(String position) {
        final int file = position.charAt(0) - STARTING_CHARACTER_OF_FILE + STARTING_VALUE_OF_FILE;
        final int rank = position.charAt(1) - STARTING_VALUE_OF_RANK;

        return new Position(file, rank);
    }

    private static boolean canNotStart(final String command) {
        return !command.equals(START_COMMAND);
    }
}
