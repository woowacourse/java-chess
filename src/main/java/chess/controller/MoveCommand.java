package chess.controller;

import chess.domain.location.Location;

public class MoveCommand {
    Location source;
    Location target;

    public MoveCommand(String source, String target) {
        this.source = Location.of(source);
        this.target = Location.of(target);
    }

    public Location getSource() {
        return source;
    }

    public Location getTarget() {
        return target;
    }
}
