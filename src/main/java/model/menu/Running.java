package model.menu;

import model.Command;
import model.GameBoard;
import model.position.Moving;
import model.position.Position;

import java.util.List;

public class Running implements ChessStatus {

    @Override
    public ChessStatus play(List<String> input, GameBoard gameBoard) {
        Command command = Command.from(input.get(0));
        if (command == Command.END) {
            return new End();
        }
        if (command == Command.MOVE) {
            Moving moving = getMoving(input.get(1), input.get(2));
            gameBoard.move(moving);
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
