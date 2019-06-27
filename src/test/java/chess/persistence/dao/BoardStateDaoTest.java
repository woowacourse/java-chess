package chess.persistence.dao;

import chess.domain.coordinate.ChessXCoordinate;
import chess.domain.coordinate.ChessYCoordinate;
import chess.domain.PieceType;
import chess.persistence.AbstractDataSource;
import chess.persistence.MySqlDataSource;
import chess.persistence.SQLiteDataSource;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardStateDaoTest {

    private RoomDao roomDao;
    private BoardStateDao boardStateDao;

    @BeforeEach
    void init() {
        try {
            new MySqlDataSource().getConnection();
            roomDao = new RoomDao(new MySqlDataSource());
            boardStateDao = new BoardStateDao(new MySqlDataSource());
        } catch (SQLException e) {
            roomDao = new RoomDao(new SQLiteDataSource());
            boardStateDao = new BoardStateDao(new SQLiteDataSource());
        }
    }

    @Test
    void insertAndFindAndDelete() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("ㄱㄱㄱㄱ");
        room.setId(roomDao.addRoom(room));
        BoardStateDto state = new BoardStateDto();
        state.setRoomId(room.getId());
        state.setType(PieceType.ROOK_WHITE.name());
        state.setCoordX(ChessXCoordinate.B.getSymbol());
        state.setCoordY(ChessYCoordinate.RANK_4.getSymbol());
        long insertedId = boardStateDao.addState(state);
        BoardStateDto found = boardStateDao.findById(insertedId).get();
        assertThat(found.getType()).isEqualTo(state.getType());
        assertThat(found.getCoordX()).isEqualTo(state.getCoordX());
        assertThat(found.getCoordY()).isEqualTo(state.getCoordY());
        assertThat(boardStateDao.deleteById(insertedId)).isEqualTo(1);
        assertThat(boardStateDao.findById(insertedId).isPresent()).isFalse();
        boardStateDao.deleteById(insertedId);
        roomDao.deleteById(room.getId());
    }

    @Test
    void findByRoomId() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("ㄱㄱㄱㄱㄱㄱ");
        room.setId(roomDao.addRoom(room));
        BoardStateDto state1 = new BoardStateDto();
        state1.setRoomId(room.getId());
        state1.setType(PieceType.ROOK_WHITE.name());
        state1.setCoordX("b");
        state1.setCoordY("2");
        BoardStateDto state2 = new BoardStateDto();
        state2.setType(PieceType.ROOK_BLACK.name());
        state2.setCoordX("a");
        state2.setCoordY("8");
        state2.setRoomId(room.getId());
        state1.setId(boardStateDao.addState(state1));
        state2.setId(boardStateDao.addState(state2));
        List<BoardStateDto> founds = boardStateDao.findByRoomId(room.getId());
        assertThat(founds).hasSize(2);
        roomDao.deleteById(room.getId());
    }

    @Test
    void updateCoordById() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("abcdafds");
        room.setId(roomDao.addRoom(room));
        BoardStateDto state = new BoardStateDto();
        state.setType(PieceType.ROOK_WHITE.name());
        state.setCoordX(ChessXCoordinate.B.getSymbol());
        state.setCoordY(ChessYCoordinate.RANK_4.getSymbol());
        state.setRoomId(room.getId());
        long insertedId = boardStateDao.addState(state);
        state.setId(insertedId);
        state.setCoordY(ChessYCoordinate.RANK_6.getSymbol());
        assertThat(boardStateDao.updateCoordById(state)).isEqualTo(1);
        BoardStateDto found = boardStateDao.findById(insertedId).get();
        assertThat(found.getType()).isEqualTo(state.getType());
        assertThat(found.getCoordX()).isEqualTo(state.getCoordX());
        assertThat(found.getCoordY()).isEqualTo(state.getCoordY());
        roomDao.deleteById(room.getId());
    }
}
