package chess.domain.strategy.bishop;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.model.player.Color;
import chess.domain.model.piece.shape.strategy.bishop.BishopStrategy;
import chess.domain.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class BishopStrategyTest {
    @DisplayName("상하좌우로 움직일 수 없다.")
    @Nested
    class InvalidDirection {
        @DisplayName("위로 움직일 수 없다.")
        @Test
        void forward() {
            //given
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(
                    Position.from(4, 'f'),
                    Position.from(6, 'f'),
                    Color.WHITE,
                    false
            ));
        }

        @DisplayName("밑으로 움직일 수 없다.")
        @Test
        void backward() {
            //given
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(
                    Position.from(4, 'd'),
                    Position.from(3, 'd'),
                    Color.WHITE,
                    false
            ));
        }

        @DisplayName("오른쪽으로 움직일 수 없다.")
        @Test
        void right() {
            //given
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(
                    Position.from(4, 'd'),
                    Position.from(4, 'e'),
                    Color.WHITE,
                    false
            ));
        }

        @DisplayName("왼쪽으로 움직일 수 없다.")
        @Test
        void left() {
            //given
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(
                    Position.from(4, 'd'),
                    Position.from(4, 'c'),
                    Color.WHITE,
                    false
            ));
        }
    }

}
