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

    public boolean isEmpty() {
        return logs.isEmpty();
    }
}
