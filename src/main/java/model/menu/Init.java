package model.menu;

import java.util.List;
import model.Command;

public class Init {

    private Init() {

    }

    public static ChessStatus gameSetting(List<String> input) {
        Command command = Command.from(input.get(0));
        if (command == Command.START) {
            return new Running();
        }
        if (command == Command.END) {
            return new End();
        }
        throw new IllegalArgumentException("start를 입력해야 게임이 시작됩니다.");
    }
}
