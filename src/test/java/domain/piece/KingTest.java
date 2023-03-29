package domain.piece;

import domain.position.Position;
import domain.position.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Test
    @DisplayName("잘못될 목적지 입력시 예외 발생")
    void givenWrongEndPoint_thenFail() {
        //given
        final King king = new King(Color.WHITE);

        //when & then
        assertThatThrownBy(() -> king.findRoute(PositionFactory.createPosition("c3"), PositionFactory.createPosition("a1")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"c3:c2", "c3:d2", "c3:d3", "c3:d4", "c3:c4", "c3:b4", "c3:b3", "c3:b2"}, delimiter = ':')
    @DisplayName("이동할 수 있는 좌표를 입력하면 빈 리스트를 반환한다.")
    void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
        //given
        final Position source = PositionFactory.createPosition(sourceCommand);
        final Position target = PositionFactory.createPosition(targetCommand);
        final King king = new King(Color.WHITE);

        //when&then
        assertThat(king.findRoute(source, target).getRoute()).isEqualTo(Collections.EMPTY_LIST);
    }

}
