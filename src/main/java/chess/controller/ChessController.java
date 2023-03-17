package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {

    private static final String START_COMMAND = "start";
    private static final int STARTING_VALUE_OF_FILE = 1;

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();
        final Board board = new Board(boardFactory);

        final String command = InputView.readStartCommand();

        if (!command.equals(START_COMMAND)) {
            return;
        }

        final Color turn = Color.WHITE;
        startGame(board, turn);
    }

    private void startGame(final Board board, Color turn) {

        final Map<Position, Piece> boardMap = board.chessBoard();
        while (true) {
            OutputView.printBoard(boardMap);
            final List<String> moveCommand = InputView.readMoveCommand();

            final Position fromPosition = convertPositionFrom(moveCommand.get(0));
            final Position toPosition = convertPositionFrom(moveCommand.get(1));

            board.move(fromPosition, toPosition, turn);

            turn = turn.opposite();
        }
    }

    private Position convertPositionFrom(String position) {
        final int file = position.charAt(0) - 'a' + STARTING_VALUE_OF_FILE;
        final int rank = position.charAt(1) - '0';

        return new Position(file, rank);
    }
}
