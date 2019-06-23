package chess.service;

import chess.domain.AbstractBoardStateFactory;
import chess.domain.BoardStateFactory;
import chess.domain.GameResult;
import chess.domain.PieceType;
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

public class SessionServiceImpl implements SessionService {
    private GameSessionDao gameSessionDao;
    private BoardStateDao boardStateDao;

    public SessionServiceImpl() {
        DataSource ds = new DataSourceFactory().createDataSource();
        gameSessionDao = new GameSessionDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    @Override
    public GameSessionDto createSession(GameSessionDto gameSessionDto) {
        try {
            gameSessionDto.setState(GameResult.KEEP.name());
            GameSessionDto createdRoom = gameSessionDao.findById(gameSessionDao.addSession(gameSessionDto))
                .orElseThrow(() -> new IllegalStateException("방 생성에 실패했습니다."));
            createBoardState(new BoardStateFactory(), createdRoom.getId());
            return createdRoom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createBoardState(AbstractBoardStateFactory boardStateFactory, long roomId) {
        boardStateFactory.create().entryStream()
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
    public Optional<GameSessionDto> findSessionById(long id) {
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
    public List<GameSessionDto> findLatestSessions(int limit) {
        try {
            return gameSessionDao.findLatestSessions(limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
