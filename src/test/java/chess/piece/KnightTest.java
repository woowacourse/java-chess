package chess.piece;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("나이트는 ㄱ자로 움직일 수 있다.")
    @Test
    void knightMovingTest() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        boolean actual = knight.isAbleToMove(
                Fixtures.E4, Fixtures.F6, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("나이트는 ㄱ자로 움직일 수 있다 더 테스트")
    @Test
    void knightMovingTestVarious() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        boolean actual1 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.F6, new HashMap<>()
        );
        boolean actual2 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.C5, new HashMap<>()
        );
        boolean actual3 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.D6, new HashMap<>()
        );
        boolean actual4 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.G5, new HashMap<>()
        );
        boolean actual5 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.G3, new HashMap<>()
        );
        boolean actual6 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.F2, new HashMap<>()
        );
        boolean actual7 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.D2, new HashMap<>()
        );
        boolean actual8 = knight.isAbleToMove(
                Fixtures.E4, Fixtures.C3, new HashMap<>()
        );

        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isTrue();
        Assertions.assertThat(actual3).isTrue();
        Assertions.assertThat(actual4).isTrue();
        Assertions.assertThat(actual5).isTrue();
        Assertions.assertThat(actual6).isTrue();
        Assertions.assertThat(actual7).isTrue();
        Assertions.assertThat(actual8).isTrue();
    }

    @DisplayName("나이트는 움직일 수 없는 곳으로 못 움직인다")
    @Test
    void knightNotMovicngTest() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        boolean actual = knight.isAbleToMove(
                Fixtures.E4, Fixtures.F5, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("나이트는 움직이려는 곳에 아군이 있다면 움직이지 못한다")
    @Test
    void knightWillNotKillFriendTest() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        boolean actual = knight.isAbleToMove(
                Fixtures.E4, Fixtures.F6, Map.of(Fixtures.F6, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("나이트는 움직이려는 곳에 적군이 있다면 움직인다")
    @Test
    void knightWillKillTest() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        boolean actual = knight.isAbleToMove(
                Fixtures.E4, Fixtures.F6, Map.of(Fixtures.F6, new Pawn(Color.BLACK))
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
