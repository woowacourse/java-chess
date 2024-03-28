package model;

import controller.menu.End;
import controller.menu.Menu;
import controller.menu.Move;
import controller.menu.Start;
import controller.menu.Status;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import model.position.Position;

public enum Command {

    START("start", input -> new Start()),
    MOVE("move", Command::toMove),
    STATUS("status", input -> new Status()),
    END("end", input -> new End());

    private static final int POSITION_SIZE = 2;

    private final String value;
    private final Function<List<String>, Menu> menu;

    Command(String value, Function<List<String>, Menu> menu) {
        this.value = value;
        this.menu = menu;
    }

    public static Menu of(List<String> input) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력하였습니다."))
                .menu.apply(input);
    }

    private static Menu toMove(List<String> input) {
        if (input.get(1).length() == POSITION_SIZE && input.get(2).length() == POSITION_SIZE) {
            return new Move(Position.from(input.get(1)), Position.from(input.get(2)));
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력하였습니다.");
    }
}
