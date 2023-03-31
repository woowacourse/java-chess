package chess.domain.strategy.king;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.model.player.Color;
import chess.domain.model.piece.shape.strategy.king.KingStrategy;
import chess.domain.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class KingStrategyTest {

    @ParameterizedTest
    @CsvSource({"3,e", "4,e", "5,e", "3,f", "5,f", "3,g", "4,g", "5,g"})
    @DisplayName("모든 방향으로 한 칸 이동할 수 있다.")
    void canMove(int rank, char filter) {
        //given
        // when, then
        assertDoesNotThrow(() -> new KingStrategy().validateDirection(
                Position.from(4, 'f'),
                Position.from(rank, filter),
                Color.WHITE,
                false
        ));
    }

    @Nested
    @DisplayName("한 칸 이상 움직일 수 없다.")
    class InvalidDistance {

        @Test
        @DisplayName("두 칸 위로 움직이기")
        void twoUp() {
            //given
            // when, then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(
                    Position.from(4, 'd'),
                    Position.from(6, 'd'),
                    Color.WHITE,
                    false
            ));
        }

        @Test
        @DisplayName("두 칸 아래로 움직이기")
        void twoBack() {
            //given
            // when, then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(
                    Position.from(4, 'd'),
                    Position.from(2, 'd'),
                    Color.WHITE,
                    false
            ));
        }

        @DisplayName("대각선으로 두 칸 움직이기")
        @Test
        void twoDiagonal() {
            //given
            // when, then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(
                    Position.from(4, 'f'),
                    Position.from(2, 'd'),
                    Color.WHITE,
                    false
            ));
        }

        @DisplayName("왼쪽으로 여러 칸 움직이기")
        @Test
        void twoLeft() {
            //given
            // when, then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(
                    Position.from(4, 'f'),
                    Position.from(4, 'a'),
                    Color.WHITE,
                    false
            ));
        }

        @DisplayName("오른쪽으로 여러 칸 움직이기")
        @Test
        void twoRight() {
            //given
            // when, then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(
                    Position.from(4, 'f'),
                    Position.from(4, 'h'),
                    Color.WHITE,
                    false
            ));
        }
    }

}
