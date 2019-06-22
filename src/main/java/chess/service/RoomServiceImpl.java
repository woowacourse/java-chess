package chess.service;

import chess.domain.AbstractStateInitiatorFactory;
import chess.domain.PieceType;
import chess.domain.StateInitiatorFactory;
import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.GameSessionDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.GameSessionDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {
    private GameSessionDao gameSessionDao;
    private BoardStateDao boardStateDao;

    public RoomServiceImpl() {
        DataSource ds = new DataSourceFactory().createDataSource();
        gameSessionDao = new GameSessionDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    @Override
    public GameSessionDto createRoom(GameSessionDto gameSessionDto) {
        try {
            GameSessionDto createdRoom = gameSessionDao.findById(gameSessionDao.addRoom(gameSessionDto))
                .orElseThrow(() -> new IllegalStateException("방 생성에 실패했습니다."));
            createBoardState(new StateInitiatorFactory(), createdRoom.getId());
            return createdRoom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createBoardState(AbstractStateInitiatorFactory boardStateInitiator, long roomId) {
        boardStateInitiator.create().entrySet().stream()
            .filter(entry -> entry.getValue().getType() != PieceType.NONE)
            .map(entry -> {
                BoardStateDto dto = new BoardStateDto();
                dto.setGameSessionId(roomId);
                dto.setCoordX(entry.getKey().getX().getSymbol());
                dto.setCoordY(entry.getKey().getY().getSymbol());
                dto.setType(entry.getValue().getType().name());
                return dto;
            })
            .forEach(this::tryInsertBoardState);
    }

    private void tryInsertBoardState(BoardStateDto dto) {
        try {
            boardStateDao.addState(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<GameSessionDto> findRoomById(long id) {
        try {
            return gameSessionDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameSessionDto> findRoomByTitle(String title) {
        try {
            return gameSessionDao.findByTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<GameSessionDto> findLatestRooms(int limit) {
        try {
            return gameSessionDao.findLatestSessions(limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
