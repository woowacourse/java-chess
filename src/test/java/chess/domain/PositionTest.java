package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    
    @Test
    @DisplayName("생성 테스트")
    void create() {
        Position position = Position.from("a1");
        assertThat(position.getFile()).isEqualTo(File.A);
        assertThat(position.getRank()).isEqualTo(Rank.ONE);
    }
    
    @Test
    @DisplayName("좌표 검증")
    void invalid_position() {
        assertThatThrownBy(() -> Position.from("z9"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}