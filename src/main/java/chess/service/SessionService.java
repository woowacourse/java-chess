package chess.service;

import chess.domain.BoardStateFactory;
import chess.domain.GameResult;
import chess.domain.RegularBoardStateFactory;
import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.GameSessionDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.GameSessionDto;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class SessionService {
    private GameSessionDao gameSessionDao;
    private BoardStateDao boardStateDao;

    public SessionService() {
        DataSource ds = DataSourceFactory.getInstance().createDataSource();
        gameSessionDao = new GameSessionDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    public GameSessionDto createSession(GameSessionDto gameSessionDto) {
        gameSessionDto.setState(GameResult.KEEP.name());
        GameSessionDto createdRoom = gameSessionDao.findById(gameSessionDao.addSession(gameSessionDto))
            .orElseThrow(() -> new IllegalStateException("방 생성에 실패했습니다."));
        createBoardState(new RegularBoardStateFactory(), createdRoom.getId());
        return createdRoom;
    }

    private void createBoardState(BoardStateFactory boardStateFactory, long sessionId) {
        boardStateFactory.create().entryStream()
            .map(entry -> {
                BoardStateDto dto = new BoardStateDto();
                dto.setCoordX(entry.getKey().getXSymbol());
                dto.setCoordY(entry.getKey().getYSymbol());
                dto.setType(entry.getValue().getType().name());
                return dto;
            })
            .forEach(dto -> tryInsertBoardState(dto, sessionId));
    }

    private void tryInsertBoardState(BoardStateDto dto, long sessionId) {
        boardStateDao.addState(dto, sessionId);
    }

    public Optional<GameSessionDto> findSessionById(long id) {
        return gameSessionDao.findById(id);
    }

    public List<GameSessionDto> findLatestSessions(int limit) {
        return gameSessionDao.findLatestSessions(limit);
    }

    public void updateSessionState(long id, GameResult stateTo) {
        GameSessionDto session = gameSessionDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게임 세션을 찾을 수 없습니다: " + id));
        session.setState(stateTo.name());
        gameSessionDao.updateSession(session);
    }
}
