package chess.domain.piece.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Position;
import chess.domain.piece.Color;
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
    }

    @DisplayName("검증 경로를 구할 때")
    @Nested
    class pathTest {

        @DisplayName("목표 지점에 상대 말이 있으면 경로에 포함하지 않는다.")
        @Test
        void calculate_Path_With_Other_Color() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.c3;
            Bishop bishop = new Bishop(Color.BLACK);

            List<Position> path = bishop.calculatePathToValidate(current, target, new Bishop(Color.WHITE));

            assertThat(path).containsOnly(CachedPosition.b2);
        }

        @DisplayName("목표 지점에 같은 팀 말이 있으면 경로에 포함한다.")
        @Test
        void calculate_Path_With_Same_Color() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.c3;
            Bishop bishop = new Bishop(Color.BLACK);

            List<Position> path = bishop.calculatePathToValidate(current, target, new Bishop(Color.BLACK));

            assertThat(path).containsOnly(CachedPosition.b2, target);
        }
    }
}
