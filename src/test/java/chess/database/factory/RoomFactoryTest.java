package chess.database.factory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoomFactoryTest {

    @Test
    void delete() {
        RoomFactory.delete();
    }

    @Test
    void updateStatus() {
        RoomFactory.updateStatus("Black", "1");
    }
}