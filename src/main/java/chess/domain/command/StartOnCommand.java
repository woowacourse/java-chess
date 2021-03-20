package chess.domain.command;

import chess.domain.ChessGame;

public class StartOnCommand implements Command {
    private static final String COMMAND_NAME = "start";
    private static final String COMMAND_MESSAGE = "체스판을 세팅했습니다.";

    @Override
    public String run(String input, ChessGame chessGame) {
        chessGame.settingBoard();
        return COMMAND_MESSAGE;
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
