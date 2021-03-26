package chess.domain.command;

import chess.domain.Game;
import java.util.Arrays;
import java.util.List;

public class Move extends BasicCommand {

    public static final String NOT_START_MESSAGE = "게임이 시작되지 않았습니다.";
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

    @Override
    public Command run(final Game game, final String command) {
        isStart(game);
        List<String> processedInput = Arrays.asList(command.split(" "));
        game.move(processedInput.get(SOURCE_INDEX), processedInput.get(TARGET_INDEX));

        if (game.isEnd()) {
            End end = new End();
            return end.run(game, command);
        }

        return new Move();
    }

    @Override
    public boolean isMoveCommand() {
        return true;
    }

    private static void isStart(final Game game) {
        if (!game.isStart()) {
            throw new IllegalArgumentException(NOT_START_MESSAGE);
        }
    }
}
