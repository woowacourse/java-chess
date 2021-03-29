package domain.command;

import domain.chessgame.ChessGame;
import java.util.List;

public class EndCommand implements Command {

    private static final String END_COMMAND = "end";

    @Override
    public void execute(ChessGame chessGame, List<String> inputs) {
        if (!chessGame.isPlaying()) {
            throw new IllegalArgumentException("[Error] end는 잘못된 명령 입니다. 현재 게임이 시작되지 않은 상태 입니다.");
        }
        chessGame.exit();
    }

    @Override
    public boolean isStatusCommand() {
        return false;
    }

    @Override
    public boolean isCommand(String command) {
        return END_COMMAND.equals(command);
    }

}
