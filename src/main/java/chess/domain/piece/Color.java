package chess.domain.piece;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Color {
    BLACK("BLACK", cheatSheets -> cheatSheets.stream().map(Direction::reverse).collect(Collectors.toList())),
    WHITE("WHITE", cheatSheets -> cheatSheets);

    private final String name;
    private final Function<List<Direction>, List<Direction>> cheatSheetChanger;

    Color(String name, Function<List<Direction>, List<Direction>> cheatSheetChanger) {
        this.name = name;
        this.cheatSheetChanger = cheatSheetChanger;
    }

    public String getName() {
        return this.name;
    }

    public List<Direction> getApplyCheatSheet(List<Direction> directions) {
        return cheatSheetChanger.apply(directions);
    }
}
