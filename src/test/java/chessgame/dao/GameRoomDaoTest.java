package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import chessgame.dto.GameRoomDto;
import chessgame.repository.GameRoomDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameRoomDaoTest {

    private final GameRoomDao gameRoomDao = new GameRoomDao();

    @Test
    @DisplayName("화이트 팀의 차례로 시작해야 하는 게임 생성")
    void addRoom() {
        assertDoesNotThrow(() -> gameRoomDao.addRoom(Camp.WHITE));
    }

    @Test
    @DisplayName("특정 아이디의 게임 데이터를 조회할 수 있다")
    void findGameRoomById() {
        GameRoomDto gameRoomDto = gameRoomDao.findGameRoomById(1);

        assertThat(gameRoomDto.getRoomId()).isEqualTo(1);
        assertThat(gameRoomDto.getTurn()).isEqualTo("WHITE");
    }

    @Test
    @DisplayName("DB에 없는 아이디인 경우 예외를 발생시킨다")
    void findGameRoomByIdException() {
        assertThatThrownBy(() -> gameRoomDao.findGameRoomById(1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("특정 아이디의 게임 데이터를 수정할 수 있다")
    void updateGameRoomById() {
        long roomId = 1;
        Camp camp = Camp.BLACK;

        assertDoesNotThrow(() -> gameRoomDao.updateGameRoomById(roomId, camp));
    }

    @Test
    @DisplayName("특정 아이디의 게임 데이터를 삭제할 수 있다")
    void deleteGameRoomById() {
        long roomId = 1;

        assertDoesNotThrow(() -> gameRoomDao.deleteGameRoomById(roomId));
    }
}
