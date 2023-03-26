package chess.service;

import chess.domain.board.Board;
import chess.domain.board.GameResult;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import chess.repository.GameDao;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameService {
    private final Map<Integer, Board> boards = new ConcurrentHashMap<>();
    private final GameDao gameDao;

    public GameService(final GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void initialize(final int roomId) {
        final Board board = new Board();
        board.initialize();
        final List<MoveDto> moves = gameDao.findAllByRoomId(roomId);
        for (MoveDto move : moves) {
            final Position source = Position.from(move.getSource());
            final Position target = Position.from(move.getTarget());
            board.move(source, target);
        }
        boards.put(roomId, board);
    }

    public void move(final MoveDto moveDto, final int roomId) {
        final Board board = boards.get(roomId);
        final Position sourcePosition = Position.from(moveDto.getSource());
        final Position targetPosition = Position.from(moveDto.getTarget());
        board.move(sourcePosition, targetPosition);
        gameDao.save(moveDto, roomId);
    }

    public boolean isGameOver(final int roomId) {
        final Board board = boards.get(roomId);
        return board.isGameOver();
    }

    public GameResult getResult(final int roomId) {
        final Board board = boards.get(roomId);
        return board.getResult();
    }
}
