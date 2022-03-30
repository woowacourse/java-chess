package chess.domain.piece.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Rook 테스트")
class RookTest {

    @DisplayName("입력된 방향에 대해")
    @Nested
    class DirectionTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Direction() {
            Position current = CachedPosition.a1;
            Position invalidTarget = CachedPosition.b3;
            Rook rook = new Rook(Color.BLACK);

            assertThatThrownBy(() -> rook.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.a3;
            Rook rook = new Rook(Color.BLACK);

            Direction actual = rook.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.N);
        }

        @DisplayName("검증 경로를 구할 때")
        @Nested
        class pathTest {

            @DisplayName("목표 지점에 상대 말이 있으면 경로에 포함하지 않는다.")
            @Test
            void calculate_Path_With_Other_Color() {
                Position current = CachedPosition.a1;
                Position target = CachedPosition.a3;
                Rook rook = new Rook(Color.BLACK);

                List<Position> path = rook.calculatePathToValidate(current, target, new Bishop(Color.WHITE));

                assertThat(path).containsOnly(CachedPosition.a2);
            }

            @DisplayName("목표 지점에 같은 팀 말이 있으면 경로에 포함한다.")
            @Test
            void calculate_Path_With_Same_Color() {
                Position current = CachedPosition.a1;
                Position target = CachedPosition.a3;
                Rook rook = new Rook(Color.BLACK);

                List<Position> path = rook.calculatePathToValidate(current, target, new Bishop(Color.BLACK));

                assertThat(path).containsOnly(CachedPosition.a2, target);
            }
        }
    }
}
