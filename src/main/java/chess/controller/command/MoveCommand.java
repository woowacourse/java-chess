package chess.controller.command;

import chess.controller.ChessController;

import java.util.List;

public class MoveCommand implements Command {
    private final List<String> parameters;

    public MoveCommand(final List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute(final ChessController chessController) {
        chessController.move(parameters);
    }
}
