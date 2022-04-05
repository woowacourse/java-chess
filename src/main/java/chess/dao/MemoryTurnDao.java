package chess.dao;

import chess.web.dto.TurnDto;
import java.util.ArrayList;
import java.util.List;

public class MemoryTurnDao implements TurnDao {

    private static final List<TurnDto> store = new ArrayList<>(List.of(new TurnDto("ready")));

    @Override
    public void save(TurnDto turnDto) {
        store.add(turnDto);
    }

    @Override
    public TurnDto findLastTurn() {
        return store.get(store.size() - 1);
    }
}
