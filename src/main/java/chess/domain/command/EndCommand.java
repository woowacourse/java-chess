package chess.domain.command;

import chess.domain.game.ChessGame;

public class EndCommand extends CommandInit {
    private static final String COMMAND = "end";

    public EndCommand(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void execute(final String input) {
        chessGame.end();
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

}
