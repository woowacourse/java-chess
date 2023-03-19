package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_COMMAND_INDEX;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;

import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private final ChessGame chessGame;

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        OutputView.printGameStart();
        Command command = getInitialCommand();
        while (command != END) {
            command = play();
        }
    }

    private Command getInitialCommand() {
        final Command command = Command.getValidate(InputView.readCommand(), START, END);
        if (command == START) {
            OutputView.printBoard(chessGame.getBoard());
        }
        return command;
    }

    private Command play() {
        final List<String> commands = InputView.readMoveCommand();
        final Command command = Command.getValidate(commands.get(MOVE_COMMAND_INDEX), MOVE, END);
        if (command == MOVE) {
            chessGame.move(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
            OutputView.printBoard(chessGame.getBoard());
        }
        return command;
    }
}
