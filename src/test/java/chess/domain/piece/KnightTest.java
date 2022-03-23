package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;


import chess.domain.Color;
import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @Test
    @DisplayName("이동 가능 여부 테스트")
    void canMove(){
        // given
        Position position1 = Position.of("b1");
        Position position2 = Position.of("c3");
        Knight knight = new Knight(Color.WHITE);
        Knight opponent = new Knight(Color.BLACK);

        // when
        List<Integer> distance = position1.calculateDistance(position2);
        boolean movable = knight.movable(distance, opponent);

        // then
        assertThat(movable).isTrue();
    }

    @Test
    @DisplayName("이동 불가능 (위치) 테스트")
    void canNotMoveCausePosition(){
        // given
        Position position1 = Position.of("b1");
        Position position2 = Position.of("c2");
        Knight knight = new Knight(Color.WHITE);
        Knight opponent = new Knight(Color.BLACK);

        // when
        List<Integer> distance = position1.calculateDistance(position2);
        boolean movable = knight.movable(distance, opponent);

        // then
        assertThat(movable).isFalse();
    }


    @Test
    @DisplayName("이동 불가능 (동일한 색) 테스트")
    void canNotMove(){
        // given
        Position position1 = Position.of("b1");
        Position position2 = Position.of("c3");
        Knight knight = new Knight(Color.WHITE);
        Knight opponent = new Knight(Color.WHITE);

        // when
        List<Integer> distance = position1.calculateDistance(position2);
        boolean movable = knight.movable(distance, opponent);

        // then
        assertThat(movable).isFalse();
    }
}