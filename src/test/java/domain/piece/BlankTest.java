package domain.piece;

import domain.coordinate.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @DisplayName("비어있는 곳에서 방향을 찾으려고 하는 경우 에러를 발생한다.")
    @Test
    void canNotGetDirectionInBlank() {
        Blank blank = new Blank();

        Coordinate start = Coordinate.from("a6");
        Coordinate destination = Coordinate.from("a8");

        Assertions.assertThatThrownBy(() -> blank.getDirection(start, destination, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 칸으로, 움직일 방향이 없습니다.");
    }
}
