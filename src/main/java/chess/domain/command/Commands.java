package chess.domain.command;

import java.util.List;

public class Commands {

    private static final int TERM_INDEX = 0;
    private static final int MOVE_COMMAND_SIZE = 3;

    private final List<String> commands;

    public Commands(final List<String> commands) {
        validateTerm(commands);
        this.commands = commands;
    }

    public boolean isStart() {
        return term().equals(Term.START.value);
    }

    public boolean isEnd() {
        return term().equals(Term.END.value);
    }

    public boolean isMove() {
        return term().equals(Term.MOVE.value);
    }

    public boolean isStatus() {
        return term().equals(Term.STATUS.value);
    }

    public boolean isRightMoveCommands() {
        return commands.size() == MOVE_COMMAND_SIZE;
    }

    public String getSource() {
        return commands.get(1);
    }

    public String getTarget() {
        return commands.get(2);
    }

    private String term() {
        return commands.get(0);
    }

    private void validateTerm(final List<String> commands) {
        if (!Term.words().contains(commands.get(TERM_INDEX))) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다");
        }
    }
}
