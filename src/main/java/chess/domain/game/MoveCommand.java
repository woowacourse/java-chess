package chess.domain.game;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand {

    private final static int SPLIT_UNIT = 4;

    private String commands;

    public MoveCommand() {
        commands = "";
    }

    public MoveCommand(String commands) {
        this.commands = commands;
    }

    public void addMoveCommand(String moveCommand) {
        commands = commands + moveCommand;
    }

    public List<String> interpretMoveCommands() {
        List<String> moveCommands = new ArrayList<>();
        for (int startIndex = 0; startIndex < commands.length(); startIndex += SPLIT_UNIT) {
            String moveCommand = commands.substring(startIndex, Math.min(startIndex + SPLIT_UNIT, commands.length()));
            moveCommands.add(moveCommand);
        }
        return moveCommands;
    }

    public void clear() {
        commands = "";
    }

    public String getCommands() {
        return commands;
    }
}
