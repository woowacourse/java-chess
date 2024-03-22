package model.menu;

import java.util.List;
import model.ChessGame;
import model.Command;
import model.position.Moving;
import model.position.Position;

public class Running implements ChessStatus {

    @Override
    public ChessStatus play(final List<String> input, final ChessGame chessGame) {
        Command command = Command.from(input.get(0));
        if (command == Command.END) {
            return new End();
        }
        if (command == Command.MOVE) {
            Moving moving = getMoving(input.get(1), input.get(2));
            chessGame.move(moving);
            return new Running();
        }
        throw new IllegalArgumentException("이미 게임이 진행중입니다.");
    }

    private Moving getMoving(final String currentPosition, final String nextPosition) {
        return new Moving(Position.from(currentPosition), Position.from(nextPosition));
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
