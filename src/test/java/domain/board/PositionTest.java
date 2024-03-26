package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.info.File;
import domain.piece.info.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    @DisplayName("좌표값이 주어지면 새 Position 인스턴스를 생성한다.")
    void create() {
        final String positionValue = "a1";
        final Position position = Position.createPosition(positionValue);
        final Position expected = new Position(File.of("A"), Rank.of(0));

        assertThat(position).isEqualTo(expected);
    }
}
