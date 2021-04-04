package chess.domain.command;

import chess.domain.game.ChessGame;

public class StartCommand extends CommandInit {
    private static final String COMMAND = "start";

    public StartCommand(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void execute(final String input) {
        chessGame.start();
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

}
