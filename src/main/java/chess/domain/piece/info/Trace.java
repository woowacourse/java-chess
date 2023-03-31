package chess.domain.piece.info;

import chess.domain.Turn;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Trace {

    private final List<Log> logs;

    public Trace() {
        this.logs = new ArrayList<>();
    }

    public void add(final Turn turn, final Position position) {
        logs.add(new Log(turn, position));
    }

    public boolean isSoonMovedTwo(final Turn turn, final Position position) {
        if (logs.size() == 0) {
            return false;
        }
        return logs.get(logs.size() - 1).isSoonMovedTwo(turn, position);
    }

    public boolean hasLog() {
        return !logs.isEmpty();
    }
}
