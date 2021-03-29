package domain.command;

import domain.chessgame.ChessGame;
import domain.position.Position;
import java.util.List;

public class MoveCommand implements Command {

    public static final String MOVE_COMMAND = "move";

    @Override
    public void execute(ChessGame chessGame, List<String> inputs) {
        if (!chessGame.isPlaying()) {
            throw new IllegalArgumentException("[Error] move는 잘못된 명령 입니다. 현재 게임이 시작되지 않은 상태 입니다.");
        }
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("[Error] 잘못된 move 명령 입니다. ex) move a7 a6");
        }
        chessGame.move(new Position(inputs.get(1)), new Position(inputs.get(2)));
    }


    @Override
    public boolean isStatusCommand() {
        return false;
    }

    @Override
    public boolean isCommand(String command) {
        return MOVE_COMMAND.equals(command);
    }

}
