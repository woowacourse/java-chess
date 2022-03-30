package chess.view.converter;

import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
import chess.domain.command.Start;
import chess.domain.command.Status;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public enum CommandConverter {

    START("start", new Start()),
    END("end", new End()),
    STATUS("status", new Status()),
    MOVE("move");

    private static final String INVALID_COMMAND = "유효한 커맨드가 아닙니다.";
    private static final String DELIMITER = " ";
    private static final String INVALID_LENGTH = "좌표는 2글자여야 합니다.";

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private static final int POSITION_INPUT_LENGTH = 2;

    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;

    private final String input;
    private Command command;

    CommandConverter(String input, Command command) {
        this.input = input;
        this.command = command;
    }

    CommandConverter(String input) {
        this.input = input;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(value -> input.equals(value.input) && !input.equals(MOVE.input))
                .map(value -> value.command)
                .findAny()
                .orElseGet(() -> checkIsMoveCommand(input));
    }

    private static Command checkIsMoveCommand(String input) {
        List<String> commands = Arrays.asList(input.split(DELIMITER));
        if (input.startsWith(MOVE.input) && commands.size() == MOVE_COMMAND_SIZE) {
            return new Move(
                    convertToPosition(commands.get(FROM_POSITION_INDEX)),
                    convertToPosition(commands.get(TO_POSITION_INDEX))
            );
        }
        throw new IllegalArgumentException(INVALID_COMMAND);
    }

    private static Position convertToPosition(String input) {
        List<String> fileAndRank = Arrays.asList(input.split(""));
        validatePositionInputLength(fileAndRank);

        Rank rank = Rank.from(fileAndRank.get(RANK_INDEX));
        File file = File.from(fileAndRank.get(FILE_INDEX));

        return new Position(rank.getRow(), file.getColumn());
    }

    private static void validatePositionInputLength(List<String> fileAndRank) {
        if (fileAndRank.size() != POSITION_INPUT_LENGTH) {
            throw new IllegalArgumentException(INVALID_LENGTH);
        }
    }
}
