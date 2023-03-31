package database;

import domain.Room;
import domain.board.Board;
import domain.piece.Rook;
import domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.position.PositionFixture.A1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Disabled
class DbChessDaoTest {

    @BeforeEach()
    void setUp() {
        new DbChessDao().clear();
    }

    @DisplayName("모든 방을 찾을 수 있다")
    @Test
    void findAllRooms() {
        //given
        final DbChessDao chessDao = new DbChessDao();

        //when
        chessDao.saveRoom(new Room("abc"));
        chessDao.saveRoom(new Room("abc"));
        chessDao.saveRoom(new Room("abc"));

        //then
        assertAll(
                () -> assertThat(chessDao.findAllRooms()).hasSize(3),
                () -> assertThat(chessDao.findAllRooms()).allMatch(room -> room.getName().equals("abc")));
    }

    @DisplayName("방을 id 값으로 찾을 수 있다")
    @Test
    void findRoom() {
        //given
        final DbChessDao chessDao = new DbChessDao();

        //when
        final Room room = chessDao.saveRoom(new Room("abc"));

        //then
        assertThat(chessDao.findRoomById(room.getId())).isEqualTo(room);
    }

    @DisplayName("방을 삭제할 수 있다")
    @Test
    void deleteRoom() {
        //given
        final DbChessDao chessDao = new DbChessDao();
        final Room room = chessDao.saveRoom(new Room("abc"));

        //when
        chessDao.deleteRoom(room.getId());

        //then
        assertThat(chessDao.findAllRooms()).isEmpty();
    }

    @DisplayName("해당 방의 모든 Piece들을 저장할 수 있다")
    @Test
    void saveBoard() {
        //given
        final DbChessDao chessDao = new DbChessDao();
        final Room room = chessDao.saveRoom(new Room("abc"));

        //when
        chessDao.saveBoard(Map.of(A1, new Rook(Team.BLACK)), room.getId());

        //then
        assertAll(
                () -> assertThat(chessDao.findBoardByRoomId(room.getId()).getPieces())
                        .containsKey(A1),
                () -> assertThat(chessDao.findBoardByRoomId(room.getId()).getPieces())
                        .containsValue(new Rook(Team.BLACK)));
    }

    @DisplayName("해당 방의 모든 Piece들을 삭제할 수 있다")
    @Test
    void deleteBoard() {
        //given
        final DbChessDao chessDao = new DbChessDao();
        final Room room = chessDao.saveRoom(new Room("abc"));
        chessDao.saveBoard(Map.of(A1, new Rook(Team.BLACK)), room.getId());

        //when
        final Board previousBoard = chessDao.findBoardByRoomId(room.getId());
        chessDao.deleteBoard(room.getId());

        //then
        assertAll(
                () -> assertThat(previousBoard).isNotNull(),
                () -> assertThat(chessDao.findBoardByRoomId(room.getId()).getPieces()).isEmpty());
    }
}
