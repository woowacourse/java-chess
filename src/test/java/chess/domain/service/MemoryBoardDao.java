package chess.domain.service;

import chess.domain.repository.BoardDao;
import chess.domain.repository.entity.BoardEntity;
import java.util.ArrayList;
import java.util.List;

class MemoryBoardDao extends BoardDao {
    private final static List<BoardEntity> boardEntities = new ArrayList<>();

    @Override
    public Long saveBoard(String turn) {
        boardEntities.add(new BoardEntity(turn));
        return 1L;
    }

    @Override
    public boolean existBoard() {
        return !boardEntities.isEmpty();
    }

    @Override
    public BoardEntity findExistBoard() {
        if (boardEntities.isEmpty()) {
            throw new IllegalStateException();
        }

        return boardEntities.get(0);
    }

    @Override
    public void deleteAll() {
        boardEntities.clear();
    }

    @Override
    public void updateCamp(String currentCamp) {
        boardEntities.clear();
        boardEntities.add(new BoardEntity(currentCamp));
    }
}
