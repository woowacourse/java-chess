package domain.squarestatus;

import domain.position.Position;
import domain.piece.Color;
import domain.type.EmptyType;
import domain.type.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmptyTest {

    @Test
    @DisplayName("findRoute 호출 시 예외가 발생한다.")
    void findRouteTest() {
        //given
        final Empty empty = new Empty(EmptyType.EMPTY);

        //when & then
        assertThatThrownBy(() -> empty.findRoute(Position.of(0, 0), Position.of(1, 1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("기물이 없습니다.");
    }

    @Test
    @DisplayName("isSameColor 호출 시 예외가 발생한다.")
    void isSameColorTest() {
        //given
        final Empty empty = new Empty(EmptyType.EMPTY);

        //when & then
        assertThatThrownBy(() -> empty.isSameColor(Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("기물이 없습니다.");
    }

    @Test
    @DisplayName("isDifferentColor 호출 시 예외가 발생한다.")
    void isDifferentColorTest() {
        //given
        final Empty empty = new Empty(EmptyType.EMPTY);

        //when & then
        assertThatThrownBy(() -> empty.isDifferentColor(Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("기물이 없습니다.");
    }

    @Nested
    @DisplayName("isSameType 호출 시,")
    class IsSameTypeTest {

        @Test
        @DisplayName("같은 타입이면 true를 반환한다.")
        void givenEmptyType_thenTrue() {
            //given
            final Empty empty = new Empty(EmptyType.EMPTY);

            //when
            final boolean isEmptyType = empty.isSameType(EmptyType.EMPTY);

            //then
            assertThat(isEmptyType).isTrue();
        }

        @Test
        @DisplayName("다른 타입이면 False를 반환한다.")
        void givenPieceType_thenFalse() {
            //given
            final Empty empty = new Empty(EmptyType.EMPTY);

            //when
            final boolean isEmptyType = empty.isSameType(PieceType.PAWN);

            //then
            assertThat(isEmptyType).isFalse();
        }

    }

    @Nested
    @DisplayName("isDifferentType 호출 시,")
    class IsDifferentTypeTest {

        @Test
        @DisplayName("다른 타입이면 true를 반환한다.")
        void givenPieceType_thenTrue() {
            //given
            final Empty empty = new Empty(EmptyType.EMPTY);

            //when
            final boolean isEmptyType = empty.isDifferentType(PieceType.PAWN);

            //then
            assertThat(isEmptyType).isTrue();
        }

        @Test
        @DisplayName("같은 타입이면 False를 반환한다.")
        void givenPieceType_thenFalse() {
            //given
            final Empty empty = new Empty(EmptyType.EMPTY);

            //when
            final boolean isEmptyType = empty.isDifferentType(EmptyType.EMPTY);

            //then
            assertThat(isEmptyType).isFalse();
        }

    }

}
