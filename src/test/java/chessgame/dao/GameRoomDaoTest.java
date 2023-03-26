package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRoomDaoTest {

    private final GameRoomDao gameRoomDao = new GameRoomDao();

    @Test
    @DisplayName("화이트 팀의 차례로 시작해야 하는 게임 생성")
    void addRoom() {
        gameRoomDao.addRoom(Camp.WHITE);
    }
}
