package chessgame;

import chessgame.domain.Command;
import chessgame.domain.point.Point;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static chessgame.point.PointFixture.A1;
import static chessgame.point.PointFixture.A2;

public class CommandTest {
    @Test
    @DisplayName("start입력 시 정상적으로 Command가 입력된다.")
    void Should_NoThrowException_When_CommandIsStart() {
        Assertions.assertThatNoException().isThrownBy(() -> Command.of("start"));
    }

    @ParameterizedTest(name = "name : {0}일 경우 생성 실패 한다.")
    @ValueSource(strings = {" ", "", "ocean", "start a1", "endend"})
    void Should_ThrowException_When_CommandIsWrong(String input) {
        Assertions.assertThatThrownBy(() -> Command.of(input)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("move입력 시 정상적으로 Command가 입력된다.")
    void Should_NoThrowException_When_CommandIsMove() {
        Command command = Command.of("move a1 a2");

        List<Point> points = List.of(A1, A2);

        Assertions.assertThat(command.getSourcePoint()).isEqualTo(points.get(0));
        Assertions.assertThat(command.getTargetPoint()).isEqualTo(points.get(1));
    }

    @ParameterizedTest(name = "name : {0}일 경우 생성 실패 한다.")
    @ValueSource(strings = {"move", "move a1", "move z1 z2", "move a1 a2 a3", "move a10 a11"})
    void Should_NoThrowException_When_MoveCommandIsWrong(String input) {
        Assertions.assertThatThrownBy(() -> Command.of(input)).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
