package chess.domain.command;

import chess.domain.ChessGame;

public class StartOnCommand extends MainCommand {
    private static final String COMMAND_NAME = "start";
    private static final String COMMAND_MESSAGE = "체스판을 세팅했습니다.";

    public StartOnCommand(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public String run(String input) {
        getChessGame().settingBoard();
        return COMMAND_MESSAGE;
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
