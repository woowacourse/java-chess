package chess.model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum ChessCommand {

    START,
    MOVE,
    STATUS,
    END,
    ;

    public static ChessCommand findCommand(List<String> rawCommand) {
        return Arrays.stream(ChessCommand.values())
                .filter(chessCommand -> chessCommand.name().toLowerCase(Locale.ROOT).equals(rawCommand.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("잘못된 커맨드입니다. %s", rawCommand)));
    }
}
