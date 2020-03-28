package piece.direction;

import chess.domain.piece.direction.Direction;
import chess.domain.piece.direction.DirectionsType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DirectionsTypeTest {

    @ParameterizedTest
    @DisplayName("필드 변수에 갖고 있는 Direction 클래스가 입력되면 true를 반환해야 함")
    @CsvSource(value = {"0,1", "1,0", "0,-1", "-1,0"})
    void inputMemberDirectionThenReturnTrue(int xPoint, int yPoint) {
        Direction direction = Direction.of(xPoint, yPoint);
        Assertions.assertThat(DirectionsType.LINEAR.contains(direction)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 Direction 클래스가 입력되면 false를 반환해야 함")
    @CsvSource(value = {"0,1", "1,0", "0,-1", "-1,0"})
    void inputNotMemberDirectionThenReturnFalse(int xPoint, int yPoint) {
        Direction direction = Direction.of(xPoint, yPoint);
        Assertions.assertThat(DirectionsType.DIAGONAL.contains(direction)).isFalse();
    }
}
