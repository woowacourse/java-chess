package model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import model.menu.End;
import model.menu.Menu;
import model.menu.Move;
import model.menu.Start;
import model.position.Position;

public enum Command {

    START("start", input -> new Start()),
    MOVE("move", Command::toMove),
    END("end", input -> new End());

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
        if (input.get(1).length() == 2 && input.get(2).length() == 2) {
            return new Move(Position.from(input.get(1)), Position.from(input.get(2)));
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력하였습니다.");
    }
}
