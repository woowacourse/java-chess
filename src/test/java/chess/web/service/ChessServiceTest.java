package chess.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.Room;
import chess.web.dao.board.FakeBoardDao;
import chess.web.dao.room.FakeRoomDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private ChessService chessService;
    private FakeRoomDao roomDao;
    private FakeBoardDao boardDao;

    @BeforeEach
    void setUp() {
        roomDao = new FakeRoomDao();
        boardDao = new FakeBoardDao();
        chessService = new ChessService(roomDao, boardDao);
    }

    @DisplayName("체스서비스를 통해 room과 board를 생성한다.")
    @Test
    void create_room_and_board() {
        Assertions.assertDoesNotThrow(
            () -> chessService.createRoomAndBoard("방이름")
        );
    }

    @DisplayName("체스서비스를 통해 room의 Name을 바꿀 수 있다.")
    @Test
    void update_room_name() {
        chessService.createRoomAndBoard("방이름");

        chessService.updateRoomName("1", "바꾼방이름");
        final Room room = roomDao.findById(1);

        assertThat(room.getName()).isEqualTo("바꾼방이름");
    }

    @AfterEach
    void tearDown() {
        chessService.removeRoom("1");
    }
}
