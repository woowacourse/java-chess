package chess.database.factory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoomFactoryTest {
    @Test
    void findByPosition() {
        String roomId = RoomFactory.findByPosition("1");
        assertThat(!roomId.isBlank()).isTrue();
    }
}