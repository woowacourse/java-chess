package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VerticalTest {

    @Test
    @DisplayName("문자열로 세로열(vertical)로 변환된다.")
    void parseTest() {
        //given
        Vertical vertical = Vertical.parse("1");

        //then
        assertThat(vertical).isEqualTo(Vertical.ONE);
    }

    @Test
    @DisplayName("세로열 번호로 객체 가져올 수 있다.")
    void ofTest() {
        //given
        Vertical vertical = Vertical.of(1);

        //then
        assertThat(vertical).isEqualTo(Vertical.ONE);
    }

    @Test
    @DisplayName("세로열 위치값을 가져온다.")
    void getIndexTest() {
        //given
        int index = Vertical.ONE.getIndex();

        //then
        assertThat(index).isEqualTo(1);
    }

    @Test
    @DisplayName("세로열 간의 거리를 구할 수 있다.")
    void getDistanceTest() {
        //given
        int distance = Vertical.ONE.getDistance(Vertical.EIGHT);

        //then
        assertThat(distance).isEqualTo(7);
    }

    @Test
    @DisplayName("세로열에 거리를 더해서 다른 세로열을 가져온다.")
    void addTest() {
        //given
        Vertical vertical = Vertical.ONE.add(3);

        //then
        assertThat(vertical).isEqualTo(Vertical.FOUR);
    }
}