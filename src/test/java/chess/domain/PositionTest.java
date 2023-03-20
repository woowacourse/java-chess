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
        assertThat(position.getFileIndex()).isEqualTo(File.A.getIndex());
        assertThat(position.getRankIndex()).isEqualTo(Rank.ONE.getIndex());
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
        Position ceneter = Position.from("d4");
        Position n = Position.from("d5");
        Position ne = Position.from("e5");
        Position e = Position.from("e4");
        Position se = Position.from("e3");
        Position s = Position.from("d3");
        Position sw = Position.from("c3");
        Position w = Position.from("c4");
        Position nw = Position.from("c5");
        Assertions.assertThat(ceneter.calculateDirection(n)).isEqualTo(Direction.N);
        Assertions.assertThat(ceneter.calculateDirection(ne)).isEqualTo(Direction.NE);
        Assertions.assertThat(ceneter.calculateDirection(e)).isEqualTo(Direction.E);
        Assertions.assertThat(ceneter.calculateDirection(se)).isEqualTo(Direction.SE);
        Assertions.assertThat(ceneter.calculateDirection(s)).isEqualTo(Direction.S);
        Assertions.assertThat(ceneter.calculateDirection(sw)).isEqualTo(Direction.SW);
        Assertions.assertThat(ceneter.calculateDirection(w)).isEqualTo(Direction.W);
        Assertions.assertThat(ceneter.calculateDirection(nw)).isEqualTo(Direction.NW);
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