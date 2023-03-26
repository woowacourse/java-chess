package chess.dao;

import chess.dto.MoveDto;
import java.util.ArrayList;
import java.util.List;

public class HistoryFakeDao implements ChessDao {
    private final List<MoveDto> moveDtos = new ArrayList<>();

    @Override
    public void saveHistory(MoveDto moveDto) {
        moveDtos.add(moveDto);
    }

    @Override
    public void deleteAllHistory() {
        moveDtos.clear();
    }

    @Override
    public List<MoveDto> selectAllHistory() {
        return moveDtos;
    }
}
