package chess.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    @Test
    @DisplayName("createBoard 테스트")
    void createBoard() {
        assertThat(BoardFactory.createBoard()).isInstanceOf(List.class);
    }
}
