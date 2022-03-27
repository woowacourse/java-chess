package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.PositionMock;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Queen 테스트")
class QueenTest {

    @DisplayName("입력된 방향에 대해")
    @Nested
    class DirectionTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Direction() {
            Position current = PositionMock.a1;
            Position invalidTarget = PositionMock.b3;
            Queen queen = new Queen(Color.BLACK);

            assertThatThrownBy(() -> queen.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = PositionMock.a1;
            Position target = PositionMock.c3;
            Queen queen = new Queen(Color.BLACK);

            Direction actual = queen.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.NE);
        }
    }
}
