package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
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
    
    @Test
    @DisplayName("방향 생성")
    void get_direction() {
        Position center = Position.from("d4");
        Position n = Position.from("d6");
        Position ne = Position.from("f6");
        Position e = Position.from("f4");
        Position se = Position.from("f2");
        Position s = Position.from("d2");
        Position sw = Position.from("b2");
        Position w = Position.from("b4");
        Position nw = Position.from("b6");
        Assertions.assertThat(center.calculateDirection(n)).isEqualTo(Direction.N);
        Assertions.assertThat(center.calculateDirection(ne)).isEqualTo(Direction.NE);
        Assertions.assertThat(center.calculateDirection(e)).isEqualTo(Direction.E);
        Assertions.assertThat(center.calculateDirection(se)).isEqualTo(Direction.SE);
        Assertions.assertThat(center.calculateDirection(s)).isEqualTo(Direction.S);
        Assertions.assertThat(center.calculateDirection(sw)).isEqualTo(Direction.SW);
        Assertions.assertThat(center.calculateDirection(w)).isEqualTo(Direction.W);
        Assertions.assertThat(center.calculateDirection(nw)).isEqualTo(Direction.NW);
    }
    
    @Test
    @DisplayName("방향 계산")
    void add_direction() {
        Position d4 = Position.from("d4");
        Direction n = Direction.N;
        Assertions.assertThat(d4.addDirection(n)).isEqualTo(Position.from("d5"));
        Direction sw = Direction.SW;
        Assertions.assertThat(d4.addDirection(sw)).isEqualTo(Position.from("c3"));
    }
}
