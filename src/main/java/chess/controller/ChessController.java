package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.common.IndexCommand.POSITION_COLUMN;
import static chess.common.IndexCommand.POSITION_ROW;
import static chess.common.IndexCommand.SOURCE_POSITION;
import static chess.common.IndexCommand.START_COMMAND_INDEX;
import static chess.common.IndexCommand.TARGET_POSITION;

public class ChessController {

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

            final String startCommand = moveCommand.get(START_COMMAND_INDEX.value());

            if (Command.isEnd(startCommand)) {
                break;
            }

            turn = movePiece(board, turn, moveCommand, startCommand);
        }
    }

    private Color movePiece(final Board board, Color turn, final List<String> moveCommands, final String startCommand) {
        if (Command.isMove(startCommand)) {
            final Position fromPosition = convertPositionFrom(moveCommands.get(SOURCE_POSITION.value()));
            final Position toPosition = convertPositionFrom(moveCommands.get(TARGET_POSITION.value()));

            board.move(fromPosition, toPosition, turn);

            turn = turn.opposite();
        }

        return turn;
    }

    private Position convertPositionFrom(String moveCommand) {
        return new Position(moveCommand.charAt(POSITION_COLUMN.value()), moveCommand.charAt(POSITION_ROW.value()));
    }
}
