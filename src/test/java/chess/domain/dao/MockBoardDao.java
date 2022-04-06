package chess.domain.dao;

import chess.domain.Color;
import java.util.HashMap;
import java.util.Map;

public class MockBoardDao implements BoardDao{

    private final Map<Integer, FakeBoard> fakeBoard = new HashMap<>();

    @Override
    public int save(Color turn) {
        fakeBoard.put(1, new FakeBoard(turn.ordinal()));
        return 1;
    }

    @Override
    public Color getCurrentTurn() {
        FakeBoard fakeBoard = this.fakeBoard.get(1);
        return Color.from(fakeBoard.getTurn());
    }
}
