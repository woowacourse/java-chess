package chess.piece;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("킹은 대각선, 직선 상관 없이 1칸 이내로 이동 가능하다.")
    @Test
    void kingMoveTest() {
        // given
        King king = new King(Color.WHITE);

        // when
        boolean actual1= king.isAbleToMove(
                Fixtures.A1, Fixtures.A2, new HashMap<>()
        );
        boolean actual2 = king.isAbleToMove(
                Fixtures.A1, Fixtures.B2, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual1 && actual2).isTrue();
    }

    @DisplayName("이동할 수 없는 위치로는 이동 불가능하다")
    @Test
    void kingNotMoveTest() {
        // given
        King king = new King(Color.WHITE);

        // when
        boolean actual1= king.isAbleToMove(
                Fixtures.A1, Fixtures.A3, new HashMap<>()
        );
        boolean actual2 = king.isAbleToMove(
                Fixtures.A1, Fixtures.C1, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual1 || actual2).isFalse();
    }

    @DisplayName("이동하는 위치에 아군이 있으면 이동 불가능하다")
    @Test
    void kingWillNotKillHisChild() {
        // given
        King king = new King(Color.WHITE);

        // when
        boolean actual = king.isAbleToMove(
                Fixtures.A1, Fixtures.A2, Map.of(Fixtures.A2, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("이동하는 위치에 적군이 있으면 이동 불가능하다")
    @Test
    void kingWillKillEnemy() {
        // given
        King king = new King(Color.WHITE);

        // when
        boolean actual = king.isAbleToMove(
                Fixtures.A1, Fixtures.A2, Map.of(Fixtures.A2, new Pawn(Color.BLACK))
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
