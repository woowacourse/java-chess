package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private static final int STARTING_VALUE_OF_FILE = 1;
    private static final char STARTING_CHARACTER_OF_FILE = 'a';
    private static final char STARTING_VALUE_OF_RANK = '0';
    private static final int START_COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();
        final Board board = new Board(boardFactory);

        final String command = InputView.readStartCommand();

        if (Command.isNotStart(command)) {
            return;
        }

        final Color turn = Color.WHITE;
        startGame(board, turn);
    }

    private void startGame(final Board board, Color turn) {

        while (true) {
            OutputView.printBoard(board.chessBoard());

            final List<String> moveCommand = InputView.readMoveCommand();

            final String startCommand = moveCommand.get(START_COMMAND_INDEX);

            if (Command.isEnd(startCommand)) {
                break;
            }

            turn = movePiece(board, turn, moveCommand, startCommand);
        }
    }

    private Color movePiece(final Board board, Color turn, final List<String> moveCommand, final String startCommand) {
        if (Command.isMove(startCommand)) {
            final Position fromPosition = convertPositionFrom(moveCommand.get(SOURCE_POSITION_INDEX));
            final Position toPosition = convertPositionFrom(moveCommand.get(TARGET_POSITION_INDEX));

            board.move(fromPosition, toPosition, turn);

            turn = turn.opposite();
        }

        return turn;
    }

    private Position convertPositionFrom(String position) {
        final int file = position.charAt(0) - STARTING_CHARACTER_OF_FILE + STARTING_VALUE_OF_FILE;
        final int rank = position.charAt(1) - STARTING_VALUE_OF_RANK;

        return new Position(file, rank);
    }
}
