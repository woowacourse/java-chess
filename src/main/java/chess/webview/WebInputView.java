package chess.webview;

import chess.controller.ApplicationCommand;
import chess.controller.InGameCommand;
import chess.domain.ChessBoardPosition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebInputView {
    private static final String IN_GAME_COMMAND_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final Map<Character, Integer> convertColumn = new HashMap<>();

    static {
        convertColumn.put('a', 1);
        convertColumn.put('b', 2);
        convertColumn.put('c', 3);
        convertColumn.put('d', 4);
        convertColumn.put('e', 5);
        convertColumn.put('f', 6);
        convertColumn.put('g', 7);
        convertColumn.put('h', 8);
    }

    private WebInputView() {}

    public static ApplicationCommand toApplicationCommand(String command) {
        return ApplicationCommand.of(command);
    }

    public static InGameCommand toInGameCommand(String command) {
        return InGameCommand.of(command.split(IN_GAME_COMMAND_DELIMITER)[COMMAND_INDEX]);
    }

    public static ChessBoardPosition extractSource(String command) {
        return coordinateToChessBoardPosition(command.split(IN_GAME_COMMAND_DELIMITER)[SOURCE_INDEX]);
    }

    public static ChessBoardPosition extractTarget(String command) {
        return coordinateToChessBoardPosition(command.split(IN_GAME_COMMAND_DELIMITER)[TARGET_INDEX]);
    }

    private static ChessBoardPosition coordinateToChessBoardPosition(String coordinate) {
        return ChessBoardPosition.of(extractColumn(coordinate), extractRow(coordinate));
    }

    private static int extractColumn(String input) {
        return convertColumn.get(input.charAt(COLUMN_INDEX));
    }

    private static int extractRow(String input) {
        return Character.getNumericValue(input.charAt(ROW_INDEX));
    }
}
