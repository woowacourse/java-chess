package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    @Test
    @DisplayName("잘못될 목적지 입력시 예외 발생")
    void givenWrongEndPoint_thenFail() {
        //given
        final Queen queen = new Queen(Color.WHITE);

        //when & then
        assertThatThrownBy(() -> queen.findRoute(MovePosition.of(PositionFactory.createPosition("c3"), PositionFactory.createPosition("a2"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"e4:e1", "e4:e7", "e4:b4", "e4:h4", "e4:b1", "e4:h1", "e4:h7", "e4:b7"}, delimiter = ':')
    @DisplayName("이동 경로를 반환한다.")
    void givenPossiblePosition_thenReturnEmptyList(String sourceCommand, String targetCommand) {
        //given
        final Position source = PositionFactory.createPosition(sourceCommand);
        final Position target = PositionFactory.createPosition(targetCommand);
        final MovePosition movePosition = MovePosition.of(source, target);
        final Queen queen = new Queen(Color.WHITE);

        //when&then
        assertThat(queen.findRoute(movePosition).getRoute()).hasSize(2);
    }

}
