package chess.controller;

import static chess.ChessGameCommand.EMPTY;
import static chess.ChessGameCommand.END;
import static chess.ChessGameCommand.MOVE;
import static chess.ChessGameCommand.START;

import chess.ChessGameCommand;
import chess.board.Board;
import chess.board.BoardFactory;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {
    private final Board board;
    private final Map<ChessGameCommand, ChessGameAction> commandMapper;

    public ChessController() {
        this.board = new BoardFactory().createInitialBoard();
        this.commandMapper = Map.of(
                START, ignore -> start(),
                MOVE, this::movePiece,
                END, ChessGameAction.EMPTY
        );
    }

    public void run() {
        ChessGameCommand command = EMPTY;

        while (command.isPlayable()) {
            List<String> commands = InputView.readCommand();

            command = ChessGameCommand.from(commands);
            commandMapper.get(command).execute(commands);
        }
    }

    private void start() {
        OutputView.printBoard(board.board());
    }

    private void movePiece(final List<String> commands) {
        Position from = searchPosition(commands.get(1));
        Position to = searchPosition(commands.get(2));

        board.move(from, to);
        OutputView.printBoard(board.board());
    }

    private Position searchPosition(final String command) {
        int fromFile = command.charAt(0) - 'a' + 1;
        int fromRank = command.charAt(1) - '0';

        return new Position(fromFile, fromRank);
    }
}
