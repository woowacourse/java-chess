package chess.domain.position;

import chess.controller.File;
import chess.controller.Rank;
import java.util.List;

public class Positions {
    private final Position from;
    private final Position to;

    public Positions(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Positions(List<String> rawPositions) {
        this(parsePosition(rawPositions.get(0)), parsePosition(rawPositions.get(1)));
    }

    private static Position parsePosition(String rawPosition) {
        int departureColumn = File.findFile(String.valueOf(rawPosition.charAt(0)));
        int departureRank = Rank.findRank(String.valueOf(rawPosition.charAt(1)));
        return new Position(departureColumn, departureRank);
    }

    public Position from() {
        return from;
    }

    public Position to() {
        return to;
    }
}
