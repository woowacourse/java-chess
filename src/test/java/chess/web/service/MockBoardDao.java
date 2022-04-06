package chess.web.service;

import chess.board.Board;
import chess.board.Team;
import chess.board.Turn;
import chess.board.piece.Pieces;
import chess.web.dao.BoardDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockBoardDao implements BoardDao {
    private final Map<Long, String> mockDb = new HashMap<>();

    public MockBoardDao(){
        mockDb.put(1L, "white");
    }

    @Override
    public Optional<Turn> findTurnById(Long id) {
        return Optional.of(new Turn(Team.from(mockDb.get(id))));
    }

    @Override
    public void updateTurnById(Long id, String newTurn) {
        mockDb.put(id, newTurn);
    }

    @Override
    public Long save() {
        return 1L;
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.of(Board.create(Pieces.createInit(), Turn.init()));
    }

    @Override
    public void deleteById(Long id) {
        mockDb.remove(id);
    }
}
