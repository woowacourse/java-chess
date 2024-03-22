package model.menu;

import model.Command;
import model.GameBoard;

import java.util.List;

public class Init implements ChessStatus {

    @Override
    public ChessStatus play(List<String> input, GameBoard gameBoard) {
        Command command = Command.from(input.get(0));
        if (command == Command.START) {
            return new Running();
        }
        if (command == Command.END) {
            return new End();
        }
        throw new IllegalArgumentException("start를 입력해야 게임이 시작됩니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
