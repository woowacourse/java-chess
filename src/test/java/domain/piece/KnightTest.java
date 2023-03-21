package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @Test
    @DisplayName("잘못될 목적지 입력시 예외 발생")
    void givenWrongEndPoint_thenFail() {
        //given
        final Knight knight = new Knight(Color.WHITE);

        //when & then
        assertThatThrownBy(() -> knight.findRoute(MovePosition.of(PositionFactory.createPosition("c3"), PositionFactory.createPosition("a1"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"c3:e4", "c3:d5", "c3:a2", "c3:d1", "c3:e2", "c3:a4", "c3:b5"}, delimiter = ':')
    @DisplayName("이동할 수 있는 좌표를 입력하면 빈 리스트를 반환한다.")
    void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
        //given
        final Position source = PositionFactory.createPosition(sourceCommand);
        final Position target = PositionFactory.createPosition(targetCommand);
        final MovePosition movePosition = MovePosition.of(source, target);
        final Knight knight = new Knight(Color.WHITE);

        //when&then
        assertThat(knight.findRoute(movePosition).getRoute()).isEqualTo(Collections.EMPTY_LIST);
    }

}
