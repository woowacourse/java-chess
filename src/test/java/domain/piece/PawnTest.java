package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Nested
    @DisplayName("기물의 색깔이 검은색일 때")
    class BlackTest {

        @Test
        @DisplayName("잘못될 목적지 입력시 예외 발생")
        void givenWrongEndPoint_thenFail() {
            //given
            final Pawn pawn = new Pawn(Color.BLACK);

            //when & then
            assertThatThrownBy(() -> pawn.findRoute(MovePosition.of(PositionFactory.createPosition("c2"), PositionFactory.createPosition("c3"))))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("잘못된 도착 지점입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"e4:d3", "e4:e3", "e4:f3"}, delimiter = ':')
        @DisplayName("이동 경로를 반환한다.")
        void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
            //given
            final Position source = PositionFactory.createPosition(sourceCommand);
            final Position target = PositionFactory.createPosition(targetCommand);
            final MovePosition movePosition = MovePosition.of(source, target);
            final Pawn pawn = new Pawn(Color.BLACK);

            //when&then
            assertThat(pawn.findRoute(movePosition).getRoute()).isEqualTo(Collections.emptyList());
        }
    }

    @Nested
    @DisplayName("기물의 색깔이 흰색일 때")
    class WhiteTest {
        @Test
        @DisplayName("잘못될 목적지 입력시 예외 발생")
        void givenWrongEndPoint_thenFail() {
            //given
            final Pawn pawn = new Pawn(Color.WHITE);

            //when & then
            assertThatThrownBy(() -> pawn.findRoute(MovePosition.of(PositionFactory.createPosition("c4"), PositionFactory.createPosition("c3"))))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("잘못된 도착 지점입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"e4:d5", "e4:e5", "e4:f5"}, delimiter = ':')
        @DisplayName("이동 경로를 반환한다.")
        void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
            //given
            final Position source = PositionFactory.createPosition(sourceCommand);
            final Position target = PositionFactory.createPosition(targetCommand);
            final MovePosition movePosition = MovePosition.of(source, target);
            final Pawn pawn = new Pawn(Color.WHITE);

            //when&then
            assertThat(pawn.findRoute(movePosition).getRoute()).isEqualTo(Collections.emptyList());
        }
    }

}
