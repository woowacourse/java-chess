package chess.domain.command;

import chess.domain.game.ChessGame;

public class StartCommand extends CommandInit {
    private static final String COMMAND = "start";

    public StartCommand(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void handle(final String input) {
        chessGame.start();
    }

    @Override
    public boolean isUsable(final String input) {
        return COMMAND.equals(input);
    }

}
