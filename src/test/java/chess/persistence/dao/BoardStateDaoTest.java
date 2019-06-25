package chess.persistence.dao;

import chess.domain.CoordinateX;
import chess.domain.CoordinateY;
import chess.domain.GameResult;
import chess.domain.boardcell.PieceType;
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
        DataSource ds = DataSourceFactory.getInstance().createDataSource();
        boardStateDao = new BoardStateDao(ds);
        gameSessionDao = new GameSessionDao(ds);
    }

    @Test
    void insertAndFindAndDelete() throws SQLException {
        GameSessionDto sess = GameSessionDto.of(0, GameResult.KEEP.name(), "room1");
        sess.setId(gameSessionDao.addSession(sess));
        BoardStateDto state = BoardStateDto.of(0, PieceType.ROOK_WHITE.name(), CoordinateX.B.getSymbol(), CoordinateY.RANK_4.getSymbol());
        state.setId(boardStateDao.addState(state, sess.getId()));
        BoardStateDto found = boardStateDao.findById(state.getId()).get();
        assertThat(found.getType()).isEqualTo(state.getType());
        assertThat(found.getCoordX()).isEqualTo(state.getCoordX());
        assertThat(found.getCoordY()).isEqualTo(state.getCoordY());
        assertThat(boardStateDao.deleteById(state.getId())).isEqualTo(1);
        assertThat(boardStateDao.findById(state.getId()).isPresent()).isFalse();
        boardStateDao.deleteById(state.getId());
        gameSessionDao.deleteById(sess.getId());
    }

    @Test
    void findByRoomId() throws SQLException {
        GameSessionDto sess = GameSessionDto.of(0, GameResult.KEEP.name(), "room2");
        BoardStateDto state1 = BoardStateDto.of(0, PieceType.ROOK_WHITE.name(), "b", "2");
        BoardStateDto state2 = BoardStateDto.of(0, PieceType.ROOK_BLACK.name(), "a", "8");
        sess.setId(gameSessionDao.addSession(sess));
        state1.setId(boardStateDao.addState(state1, sess.getId()));
        state2.setId(boardStateDao.addState(state2, sess.getId()));
        List<BoardStateDto> founds = boardStateDao.findBySessionId(sess.getId());
        assertThat(founds).hasSize(2);
        gameSessionDao.deleteById(sess.getId());
    }

    @Test
    void updateCoordById() throws SQLException {
        GameSessionDto sess = GameSessionDto.of(0, GameResult.KEEP.name(), "room3");
        sess.setId(gameSessionDao.addSession(sess));
        BoardStateDto state = BoardStateDto.of(0, PieceType.ROOK_WHITE.name(), "b", "2");
        state.setId(boardStateDao.addState(state, sess.getId()));
        state.setCoordY("4");
        assertThat(boardStateDao.updateCoordById(state)).isEqualTo(1);
        BoardStateDto found = boardStateDao.findById(state.getId()).get();
        assertThat(found.getType()).isEqualTo(state.getType());
        assertThat(found.getCoordX()).isEqualTo(state.getCoordX());
        assertThat(found.getCoordY()).isEqualTo(state.getCoordY());
        gameSessionDao.deleteById(sess.getId());
    }

    @Test
    void findByRoomIdAndCoordinate() throws SQLException {
        GameSessionDto sess = GameSessionDto.of(0, GameResult.KEEP.name(), "room4");
        sess.setId(gameSessionDao.addSession(sess));
        BoardStateDto state = BoardStateDto.of(0, PieceType.ROOK_WHITE.name(), "b", "4");
        state.setId(boardStateDao.addState(state, sess.getId()));
        Optional<BoardStateDto> found = boardStateDao.findByRoomIdAndCoordinate(sess.getId(), state.getCoordX(), state.getCoordY());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(state.getId());
        gameSessionDao.deleteById(sess.getId());
    }
}
