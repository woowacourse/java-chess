package chess.room;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class NotationDaoTest {
    @Test
    @DisplayName("기보를 생성하는 테스트")
    void addNotationTest() throws SQLException {
        Square source = new Square(File.A, Rank.TWO);
        Square target = new Square(File.A, Rank.THREE);
        NotationDao.addNotation(1, 1, new Move(source, target));
    }

    @Test
    @DisplayName("기보를 가져오는 테스트")
    void findByRoomIdTest() throws SQLException {
        NotationDao.findByRoomId(1);
    }

    @Test
    @DisplayName("기보를 지우는 테스트")
    void deleteNotationTest() throws SQLException {
        NotationDao.deleteByRoomId(11);
    }
}
