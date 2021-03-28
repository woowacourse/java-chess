package chess.domain.command;

import chess.domain.ChessGame;

public class StatusOnCommand implements Command {
    private static final String TEXT = "status";

    @Override
    public boolean isMatch(String commandText) {
        return TEXT.equals(commandText);
    }

    @Override
    public void execute(ChessGame chessGame, String[] splitCommand) {
        if (chessGame.notStartYet()) {
            throw new IllegalArgumentException("[ERROR] 아직 게임이 시작되지 않았습니다.");
        }
    }

    @Override
    public boolean isMustShowBoard() {
        return false;
    }

    @Override
    public boolean isMustShowStatus() {
        return true;
    }
}
