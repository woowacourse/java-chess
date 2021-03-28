package domain.command;

import domain.chessgame.ChessGame;
import java.util.List;

public class StatusCommand implements Command {

    public static final String STATUS_COMMAND = "status";

    @Override
    public void execute(ChessGame chessGame, List<String> inputs) {
        if (!chessGame.isPlaying()) {
            throw new IllegalArgumentException(
                "[Error] status는 잘못된 명령 입니다. 현재 게임이 시작되지 않은 상태 입니다.");
        }
    }

    @Override
    public boolean isStatusCommand() {
        return true;
    }

    @Override
    public boolean isCommand(String command) {
        return STATUS_COMMAND.equals(command);
    }

}
