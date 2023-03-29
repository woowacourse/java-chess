package domain.piece;

import domain.position.Position;
import domain.position.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @Test
    @DisplayName("잘못될 목적지 입력시 예외 발생")
    void givenWrongEndPoint_thenFail() {
        //given
        final Bishop bishop = new Bishop(Color.WHITE);

        //when & then
        assertThatThrownBy(() -> bishop.findRoute(PositionFactory.createPosition("c3"), PositionFactory.createPosition("a2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"e5:h2", "e5:h8", "e5:b8", "e5:b2"}, delimiter = ':')
    @DisplayName("이동 경로를 반환한다.")
    void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
        //given
        final Position source = PositionFactory.createPosition(sourceCommand);
        final Position target = PositionFactory.createPosition(targetCommand);
        final Bishop bishop = new Bishop(Color.WHITE);

        //when&then
        assertThat(bishop.findRoute(source, target).getRoute()).hasSize(2);
    }

}
