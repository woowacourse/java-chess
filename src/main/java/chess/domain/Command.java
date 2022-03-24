package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Rank;

public class Command {

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
        if (!command.equals("start") && !command.equals("end")) {
            checkMove(command);
        }
    }

    private static void checkMove(String command) {
        if (!command.startsWith("move")) {
            throw new IllegalArgumentException("잘못된 커멘드 입니다.");
        }

        String[] token = command.split(" ");

        if (!Rank.isRank(token[1].charAt(0)) || !File.isFile(token[1].charAt(1))
        || !Rank.isRank(token[2].charAt(0)) || !File.isFile(token[2].charAt(1))) {
            throw new IllegalArgumentException("형식이 잘못되었거나 범위를 벗어났습니다.");
        }

    }

    public boolean isEnd() {
        if (command.equals("end")) {
            return true;
        }

        return false;
    }

    public boolean isMoveCommand() {
        if (command.startsWith("move")) {
            return true;
        }

        return false;
    }
}
