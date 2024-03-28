package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TerminalPositionTest {
    @DisplayName("출발점과 도착점이 같은 경우 예외를 발생시킨다.")
    @Test
    void sameStartAndEndPointExceptionTest() {
        assertThatThrownBy(() ->
                new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.FIRST)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작점과 끝점은 같을 수 없습니다.");
    }

    @DisplayName("출발점과 도착점이 같지 않은 경우 예외가 발생하지 않는다.")
    @Test
    void sameStartAndEndPointNotExceptionTest() {
        assertThatCode(() ->
                new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.SECOND)))
                .doesNotThrowAnyException();
    }
}
