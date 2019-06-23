package chess.persistence.dao;

import chess.domain.CoordinateX;
import chess.domain.CoordinateY;
import chess.domain.PieceType;
import chess.persistence.DataSourceFactory;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.GameSessionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardStateDaoTest {

    private GameSessionDao gameSessionDao;
    private BoardStateDao boardStateDao;

    @BeforeEach
    void init() {
        DataSource ds = new DataSourceFactory().createDataSource();
        boardStateDao = new BoardStateDao(ds);
        gameSessionDao = new GameSessionDao(ds);
    }

    @Test
    void insertAndFindAndDelete() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("room for board state insert test");
        room.setId(gameSessionDao.addSession(room));
        BoardStateDto state = new BoardStateDto();
        state.setGameSessionId(room.getId());
        state.setType(PieceType.ROOK_WHITE.name());
        state.setCoordX(CoordinateX.B.getSymbol());
        state.setCoordY(CoordinateY.RANK_4.getSymbol());
        long insertedId = boardStateDao.addState(state);
        BoardStateDto found = boardStateDao.findById(insertedId).get();
        assertThat(found.getType()).isEqualTo(state.getType());
        assertThat(found.getCoordX()).isEqualTo(state.getCoordX());
        assertThat(found.getCoordY()).isEqualTo(state.getCoordY());
        assertThat(boardStateDao.deleteById(insertedId)).isEqualTo(1);
        assertThat(boardStateDao.findById(insertedId).isPresent()).isFalse();
        boardStateDao.deleteById(insertedId);
        gameSessionDao.deleteById(room.getId());
    }

    @Test
    void findByRoomId() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("room for find board states test");
        room.setId(gameSessionDao.addSession(room));
        BoardStateDto state1 = new BoardStateDto();
        state1.setGameSessionId(room.getId());
        state1.setType(PieceType.ROOK_WHITE.name());
        state1.setCoordX("b");
        state1.setCoordY("2");
        BoardStateDto state2 = new BoardStateDto();
        state2.setType(PieceType.ROOK_BLACK.name());
        state2.setCoordX("a");
        state2.setCoordY("8");
        state2.setGameSessionId(room.getId());
        state1.setId(boardStateDao.addState(state1));
        state2.setId(boardStateDao.addState(state2));
        List<BoardStateDto> founds = boardStateDao.findBySessionId(room.getId());
        assertThat(founds).hasSize(2);
        gameSessionDao.deleteById(room.getId());
    }

    @Test
    void updateCoordById() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("room for update coordinate test");
        room.setId(gameSessionDao.addSession(room));
        BoardStateDto state = new BoardStateDto();
        state.setType(PieceType.ROOK_WHITE.name());
        state.setCoordX(CoordinateX.B.getSymbol());
        state.setCoordY(CoordinateY.RANK_4.getSymbol());
        state.setGameSessionId(room.getId());
        long insertedId = boardStateDao.addState(state);
        state.setId(insertedId);
        state.setCoordY(CoordinateY.RANK_6.getSymbol());
        assertThat(boardStateDao.updateCoordById(state)).isEqualTo(1);
        BoardStateDto found = boardStateDao.findById(insertedId).get();
        assertThat(found.getType()).isEqualTo(state.getType());
        assertThat(found.getCoordX()).isEqualTo(state.getCoordX());
        assertThat(found.getCoordY()).isEqualTo(state.getCoordY());
        gameSessionDao.deleteById(room.getId());
    }

    @Test
    void findByRoomIdAndCoordinate() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("room for delete state test");
        room.setId(gameSessionDao.addSession(room));
        BoardStateDto state = new BoardStateDto();
        state.setType(PieceType.ROOK_WHITE.name());
        state.setCoordX(CoordinateX.B.getSymbol());
        state.setCoordY(CoordinateY.RANK_4.getSymbol());
        state.setGameSessionId(room.getId());
        long insertedId = boardStateDao.addState(state);
        state.setId(insertedId);
        Optional<BoardStateDto> found = boardStateDao.findByRoomIdAndCoordinate(room.getId(), state.getCoordX(), state.getCoordY());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(insertedId);
        gameSessionDao.deleteById(room.getId());
    }
}
