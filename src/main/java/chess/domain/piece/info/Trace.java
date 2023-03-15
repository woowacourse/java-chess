package chess.domain.piece.info;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Trace {

    private final List<Log> logs;

    public Trace() {
        this.logs = new ArrayList<>();
    }

    public void add(final int turn, final Position position) {
        logs.add(new Log(turn, position));
    }

    public boolean hasLog() {
        return !logs.isEmpty();
    }
}
