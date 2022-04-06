package chess.dao;

import chess.web.dto.TurnDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryTurnDao implements TurnDao {

    private static final List<TurnDto> store = new ArrayList<>();

    @Override
    public void save(TurnDto turnDto) {
        store.add(turnDto);
    }

    @Override
    public Optional<TurnDto> findLastTurn() {
        return Optional.of(store.get(store.size() - 1));
    }
}
