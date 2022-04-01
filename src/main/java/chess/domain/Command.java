package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class Command {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final String command;

    private Command(String command) {
        this.command = command;
    }

    public static Command from(String command) {
        validateCommand(command);
        return new Command(command);
    }

    private static void validateCommand(String command) {
        checkStartOrEnd(command);
    }

    private static void checkStartOrEnd(String command) {
        if (!command.equals("start") && !command.equals("end") && !command.equals("status")) {
            checkMove(command);
        }
    }

    private static void checkMove(String command) {
        if (!command.startsWith("move")) {
            throw new IllegalArgumentException("잘못된 커멘드 입니다.");
        }

        String[] token = command.split(" ");

        if ((!isPosition(token[SOURCE_INDEX])) || (!isPosition(token[TARGET_INDEX]))) {
            throw new IllegalArgumentException("형식이 잘못되었거나 범위를 벗어났습니다.");
        }
    }

    private static boolean isPosition(String token) {
        char first = token.charAt(FILE_INDEX);
        char second = token.charAt(RANK_INDEX);

        return File.isFile(first) && Rank.isRank(Character.getNumericValue(second));
    }

    public boolean isEnd() {
        return command.equals("end");
    }

    public boolean isMoveCommand() {
        return command.startsWith("move");
    }

    public boolean isStart() {
        return command.equals("start");
    }

    public boolean isStatus() {
        return command.equals("status");
    }

    public Map<String, Position> makePositions() {
        checkMoveCommand();

        Map<String, Position> positions = new HashMap<>();

        String[] token = command.split(" ");

        positions.put("source", makePosition(token, SOURCE_INDEX));
        positions.put("target", makePosition(token, TARGET_INDEX));

        return positions;
    }

    private Position makePosition(String[] token, int index) {
        File file = File.toFile(token[index].charAt(FILE_INDEX));
        Rank rank = Rank.toRank(token[index].charAt(RANK_INDEX));

        return Position.of(file, rank);
    }

    private void checkMoveCommand() {
        if (!isMoveCommand()) {
            throw new IllegalArgumentException("커맨드가 잘못되었습니다. move 커맨드가 와야 합니다.");
        }
    }
}
