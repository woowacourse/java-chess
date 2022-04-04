package chess.dao;

import chess.web.dto.TurnDto;
import java.util.ArrayList;
import java.util.List;

public class MemoryTurnDaoImpl implements TurnDao {

    private static List<TurnDto> store = new ArrayList<>();

    @Override
    public void save(TurnDto turnDto) {
        store.add(turnDto);
    }

    @Override
    public TurnDto findLastTurn() {
        return store.get(store.size() - 1);
    }

    @Override
    public boolean isFirstTurn() {
        return store.isEmpty();
    }
}
