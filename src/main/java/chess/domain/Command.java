package chess.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {
    private final GameCommand gameCommand;
    private final Location source;
    private final Location target;

    private Command(GameCommand gameCommand) {
        this.gameCommand = gameCommand;
        this.source = null;
        this.target = null;
    }

    private Command(GameCommand gameCommand, Location source, Location target) {
        this.gameCommand = gameCommand;
        this.source = source;
        this.target = target;
    }

    public static Command of(String text) {
        List<String> command = Stream.of(text.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());
        GameCommand gameCommand = GameCommand.of(text);

        if (!gameCommand.isMove()) {
            return new Command(gameCommand);
        }
        return new Command(gameCommand, Location.of(command.get(1)), Location.of(command.get(2)));
    }

    public boolean isStart() {
        return gameCommand.isStart();
    }

    public boolean isEnd() {
        return gameCommand.isEnd();
    }

    public boolean isMove() {
        return gameCommand.isMove();
    }

    public boolean isStatus() {
        return gameCommand.isStatus();
    }

    public Location getSource() {
        return source;
    }

    public Location getTarget() {
        return target;
    }
}
