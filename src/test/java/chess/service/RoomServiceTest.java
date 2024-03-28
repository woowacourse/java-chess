package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.room.Room;
import chess.repository.FakeRoomDao;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임방 로직")
class RoomServiceTest {

    FakeRoomDao roomRepository;
    RoomService roomService;

    @BeforeEach
    void setup() {
        roomRepository = new FakeRoomDao();
        roomService = new RoomService(roomRepository);
    }

    @DisplayName("모든 게임방명을 조회한다")
    @Test
    void getRoomNames() {
        //given
        long userId = 2L;

        //when
        List<String> roomNames = roomService.getRoomNames(userId);

        //then
        assertAll(
                () -> assertThat(roomNames).hasSize(2),
                () -> assertThat(roomNames).containsExactly("BigChess", "SmallChess")
        );
    }


    @DisplayName("게임방을 생성한다")
    @Test
    void create() {
        //given
        long userId = 3L;
        String roomName = "newRoom";

        //when
        long newChessRoom = roomService.create(userId, roomName);
        Room room = roomRepository.findByUserIdAndName(userId, roomName).get();

        //then
        assertAll(
                () -> assertThat(room).isNotNull(),
                () -> assertThat(newChessRoom).isEqualTo(room.getRoomId()),
                () -> assertThat(room.getName()).isEqualTo(roomName)
        );
    }

    @DisplayName("같은 사용자의 중복된 이름으로 게임방 생성 시 예외가 발생한다")
    @Test
    void duplicatedName() {
        //given
        long userId = 1L;
        String duplicatedName = "chess";

        //when & then
        assertThatThrownBy(() -> roomService.create(userId, duplicatedName)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("사용자 식별자와 게임방명으로 게임방을 조회한다")
    @Test
    void selectRoom() {
        //given
        long userId = 1L;
        String roomName = "chess";

        //when
        long roomId = roomService.selectRoom(userId, roomName);

        //then
        assertThat(roomId).isEqualTo(1L);
    }

    @DisplayName("잘못된 게임방명 입력 시 예외가 발생한다")
    @Test
    void roomNotFound() {
        //given
        long userId = 1L;
        String invalidRoomName = "chessss";

        //when & then
        assertThatThrownBy(() -> roomService.selectRoom(userId, invalidRoomName)).isInstanceOf(
                IllegalArgumentException.class);
    }
}
