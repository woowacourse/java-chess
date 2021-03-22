package chess.domain.command;

import chess.domain.ChessGame;

public class StartOnCommand implements Command {
    private static final String COMMAND_NAME = "start";
    private static final String COMMAND_MESSAGE = "체스판을 세팅했습니다.";

    public String run(ChessGame chessGame, CommandInput commandInput) {
        chessGame.settingBoard();
        chessGame.start();
        return COMMAND_MESSAGE;
    }

    @Override
    public boolean isSameCommand(CommandInput commandInput) {
        return commandInput.isSameCommand(COMMAND_NAME);
    }
}
