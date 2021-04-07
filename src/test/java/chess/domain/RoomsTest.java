package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomsTest {

    @Test
    @DisplayName("기존 방 불러오기")
    void creatNewRoom() {
        Rooms rooms = new Rooms();
        ChessGame chessGame = new ChessGame();
        rooms.addRoom("1", chessGame);

        assertThat(rooms.loadGameByRoomId("1")).isEqualTo(chessGame);
    }
}
