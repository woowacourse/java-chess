package chess.dao;

import chess.web.dto.TurnDto;
import java.util.ArrayList;
import java.util.List;

public class MemoryTurnDaoImpl implements TurnDao {

    List<TurnDto> store = new ArrayList<>();

    @Override
    public void save(TurnDto turnDto) {
        store.add(turnDto);
    }

    @Override
    public TurnDto findLastTurn() {
        return store.get(store.size() - 1);
    }
}
