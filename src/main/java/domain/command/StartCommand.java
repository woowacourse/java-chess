package domain.command;

import domain.chessgame.ChessGame;
import java.util.List;

public class StartCommand implements Command {

    public static final String START_COMMAND = "start";

    @Override
    public void execute(ChessGame chessGame, List<String> inputs) {
        if (chessGame.isPlaying()) {
            throw new IllegalArgumentException("[Error] start는 잘못된 명령 입니다. 이미 게임이 시작된 상태 입니다.");
        }
        chessGame.start();
    }

    @Override
    public boolean isStatusCommand() {
        return false;
    }

    @Override
    public boolean isCommand(String command) {
        return START_COMMAND.equals(command);
    }
}
