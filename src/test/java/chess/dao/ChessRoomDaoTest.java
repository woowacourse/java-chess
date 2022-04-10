package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.Room;
import chess.model.Board;
import chess.model.status.Running;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessRoomDaoTest {

    private final ChessRoomDao dao = new ChessRoomDao(new ConnectionManager());
    private final ChessBoardDao chessBoardDao = new ChessBoardDao(new ConnectionManager());
    private int boardId;
    private Room room;

    @BeforeEach
    void setup() {
        final Board board = chessBoardDao.save(new Board(new Running()));
        this.boardId = board.getId();
        this.room = dao.save(new Room("개초보만", boardId));
    }

    @AfterEach
    void setDown() {
        dao.deleteAll();
    }

    @Test
    void save() {
        assertAll(
                () -> assertThat(room.getTitle()).isEqualTo("개초보만"),
                () -> assertThat(room.getBoardId()).isEqualTo(boardId)
        );
    }

    @Test
    void findAll() {
        dao.save(new Room("왕허접만", boardId));
        final List<Room> boards = dao.findAll();

        assertThat(boards.size()).isEqualTo(2);
    }
}
