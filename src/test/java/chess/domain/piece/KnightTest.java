package chess.domain.piece;

import static chess.domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;


import chess.domain.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @Test
    @DisplayName("이동 가능 여부 테스트")
    void canMove(){
        // given
        Knight knight = new Knight(Color.WHITE);
        Knight opponent = new Knight(Color.BLACK);

        // when
        List<Integer> distance = B1.calculateDistance(C3);
        boolean movable = knight.movable(distance, opponent);

        // then
        assertThat(movable).isTrue();
    }

    @Test
    @DisplayName("이동 불가능 (위치) 테스트")
    void canNotMoveCausePosition(){
        // given
        Knight knight = new Knight(Color.WHITE);
        Knight opponent = new Knight(Color.BLACK);

        // when
        List<Integer> distance = B1.calculateDistance(C2);
        boolean movable = knight.movable(distance, opponent);

        // then
        assertThat(movable).isFalse();
    }


    @Test
    @DisplayName("이동 불가능 (동일한 색) 테스트")
    void canNotMove(){
        // given
        Knight knight = new Knight(Color.WHITE);
        Knight opponent = new Knight(Color.WHITE);

        // when
        List<Integer> distance = B1.calculateDistance(C3);
        boolean movable = knight.movable(distance, opponent);

        // then
        assertThat(movable).isFalse();
    }
}