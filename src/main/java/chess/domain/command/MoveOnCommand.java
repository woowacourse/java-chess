package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.position.Position;

public class MoveOnCommand implements Command {
    private static final String TEXT = "move";
    private static final int COMMAND_SIZE = 3;

    @Override
    public boolean isMatch(String commandText) {
        return TEXT.equals(commandText);
    }

    @Override
    public void execute(ChessGame chessGame, String[] splitCommand) {
        if (chessGame.notStartYet()) {
            throw new IllegalArgumentException("[ERROR] 아직 게임이 시작되지 않았습니다.");
        }
        if (splitCommand.length != COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] move source위치 target위치 형식으로 입력해야합니다.");
        }
        chessGame.movePieceFromSourceToTarget(Position.of(splitCommand[1]), Position.of(splitCommand[2]));
    }

    @Override
    public boolean isMustShowBoard() {
        return true;
    }

    @Override
    public boolean isMustShowStatus() {
        return false;
    }
}
