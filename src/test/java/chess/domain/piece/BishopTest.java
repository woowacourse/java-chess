package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Bishop 테스트")
class BishopTest {

    @DisplayName("입력된 방향에 대해")
    @Nested
    class DirectionTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Direction() {
            Position current = CachedPosition.a1;
            Position invalidTarget = CachedPosition.b3;
            Bishop bishop = new Bishop(Color.BLACK);

            assertThatThrownBy(() -> bishop.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.c3;
            Bishop bishop = new Bishop(Color.BLACK);

            Direction actual = bishop.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.NE);
        }

        @DisplayName("경로를 구한다.")
        @Test
        void calculate_Path() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.c3;
            Bishop bishop = new Bishop(Color.BLACK);

            List<Position> path = bishop.calculatePath(current, target);

            assertThat(path).containsOnly(CachedPosition.b2);
        }
    }
}
