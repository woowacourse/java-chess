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

    @DisplayName("방을 삭제할 수 있다")
    @Test
    void deleteRoom() {
        //given
        final DbChessDao chessDao = new DbChessDao();
        final long roomId = chessDao.saveRoom(new Room("abc"));

        //when
        chessDao.deleteRoom(roomId);

        //then
        assertThat(chessDao.findAllRooms()).isEmpty();
    }

    @DisplayName("해당 방의 모든 Piece들을 저장할 수 있다")
    @Test
    void saveBoard() {
        //given
        final DbChessDao chessDao = new DbChessDao();
        final long roomId = chessDao.saveRoom(new Room("abc"));

        //when
        chessDao.saveBoard(Map.of(A1, new Rook(Team.BLACK)), roomId);

        //then
        assertAll(
                () -> assertThat(chessDao.findBoardByRoomId(roomId).getPieces())
                        .containsKey(A1),
                () -> assertThat(chessDao.findBoardByRoomId(roomId).getPieces())
                        .containsValue(new Rook(Team.BLACK)));
    }

    @DisplayName("해당 방의 모든 Piece들을 삭제할 수 있다")
    @Test
    void deleteBoard() {
        //given
        final DbChessDao chessDao = new DbChessDao();
        final long roomId = chessDao.saveRoom(new Room("abc"));
        chessDao.saveBoard(Map.of(A1, new Rook(Team.BLACK)), roomId);

        //when
        final Board previousBoard = chessDao.findBoardByRoomId(roomId);
        chessDao.deleteBoard(roomId);

        //then
        assertAll(
                () -> assertThat(previousBoard).isNotNull(),
                () -> assertThat(chessDao.findBoardByRoomId(roomId).getPieces()).isEmpty());
    }
}
