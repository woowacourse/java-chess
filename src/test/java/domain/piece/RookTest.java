package domain.piece;

import domain.position.Position;
import domain.position.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookTest {

    @Test
    @DisplayName("잘못될 목적지 입력시 예외 발생")
    void givenWrongEndPoint_thenFail() {
        //given
        final Rook rook = new Rook(Color.WHITE);

        //when & then
        assertThatThrownBy(() -> rook.findRoute(PositionFactory.createPosition("c3"), PositionFactory.createPosition("a1")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"e4:e1", "e4:e7", "e4:b4", "e4:h4"}, delimiter = ':')
    @DisplayName("이동 경로를 반환한다.")
    void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
        //given
        final Position source = PositionFactory.createPosition(sourceCommand);
        final Position target = PositionFactory.createPosition(targetCommand);
        final Rook rook = new Rook(Color.WHITE);

        //when&then
        assertThat(rook.findRoute(source, target).getRoute()).hasSize(2);
    }

}
