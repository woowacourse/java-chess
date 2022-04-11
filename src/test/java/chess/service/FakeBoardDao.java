package chess.service;

import chess.dao.BoardDao;
import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FakeBoardDao implements BoardDao {

    private final Map<Long, Map<Position, Piece>> boardRepository = new LinkedHashMap<>();
    private Map<Position, Piece> fakeBoard;

    @Override
    public void saveAll(Long gameId, Board board) {
        fakeBoard = new LinkedHashMap<>();
        for (Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            save(piece.getName(), position.getPosition(), gameId);
        }
        boardRepository.put(gameId, fakeBoard);
    }

    @Override
    public void save(String name, String position, Long gameId) {
        fakeBoard.put(Position.create(position), Pieces.find(name));
    }

    @Override
    public Board findById(Long gameId) {
        return new Board(boardRepository.get(gameId));
    }

    @Override
    public void deleteByGameId(Long gameId) {
        boardRepository.remove(gameId);
    }

    @Override
    public void updateNameByGameIdAndPosition(Long gameId, String position, String name) {
        Map<Position, Piece> board = boardRepository.get(gameId);
        board.put(Position.create(position), Pieces.find(name));
        boardRepository.put(gameId, board);
    }

    public int getBoardSize() {
        return boardRepository.size();
    }
}
