package chess.domain.square;

import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.List;

public class Empty implements Square {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Position> findPassPathTaken(TerminalPosition terminalPosition) {
        throw new IllegalArgumentException("시작 위치가 비어있습니다.");
    }

    @Override
    public List<Position> findAttackPathTaken(TerminalPosition terminalPosition) {
        throw new IllegalArgumentException("시작 위치가 비어있습니다.");
    }

    @Override
    public void move() {
        throw new IllegalArgumentException("시작 위치가 비어있습니다.");
    }
}
