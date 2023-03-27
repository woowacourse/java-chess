import database.ChessDao;
import domain.Room;
import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @DisplayName("새로운 게임을 시작하면 DB에 Room이 저장된다")
    @Test
    void initGame() {
        //given
        final TestDao dao = new TestDao();

        //when
        ChessGame.initGame(dao, new Room("test"));

        //then
        assertThat(dao.findAllRooms()).hasSize(1);
    }

    @DisplayName("기존의 게임을 로드하면 DB의 Board는 제거된다")
    @Test
    void loadGame() {
        //given
        final TestDao dao = new TestDao();
        ChessGame.initGame(dao, new Room(1L, "test")).save();

        //when
        ChessGame.loadGame(dao, 1L);


        //then
        assertThat(dao.findBoardByRoomId(1L)).isNull();
    }

    @DisplayName("delete를 호출하면 DB의 Room과 Board가 제거된다")
    @Test
    void deleteGame() {
        //given
        final TestDao dao = new TestDao();
        final ChessGame chessGame = ChessGame.initGame(dao, new Room(1L, "test"));
        chessGame.save();

        //when
        chessGame.delete();

        //then
        Assertions.assertAll(
                () -> assertThat(dao.findAllRooms()).isEmpty(),
                () -> assertThat(dao.findBoardByRoomId(1L)).isNull());
    }

    private static class TestDao implements ChessDao {
        private final Map<Long, Room> rooms = new HashMap<>();
        private final Map<Long, Board> boards = new HashMap<>();
        private static long autoIncrementKey = 1L;

        @Override
        public List<Room> findAllRooms() {
            return rooms.values().stream().collect(Collectors.toUnmodifiableList());
        }

        @Override
        public long saveRoom(final Room room) {
            rooms.put(autoIncrementKey, room);
            return autoIncrementKey++;
        }

        @Override
        public void deleteRoom(final long roomId) {
            rooms.remove(roomId);
            deleteBoard(roomId);
        }

        @Override
        public void saveBoard(final Map<Position, Piece> board, final long roomId) {
            boards.put(roomId, Board.load(board));
        }

        @Override
        public void deleteBoard(final long roomId) {
            boards.remove(roomId);
        }

        @Override
        public Board findBoardByRoomId(final long roomId) {
            return boards.get(roomId);
        }

        @Override
        public void clear() {
            rooms.clear();
            boards.clear();
            autoIncrementKey = 1L;
        }
    }
}
