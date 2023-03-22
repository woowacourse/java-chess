package techcourse.fp.mission;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
class CarTest {

    @Test
    public void 이동() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(()->true);
        assertThat(actual).isEqualTo(new Car("pobi", 1));
    }

    @Test
    public void 정지() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(()->false);
        assertThat(actual).isEqualTo(new Car("pobi", 0));
    }

    @Test
    void test(){
        List<Integer> list = Arrays.asList(1,2,3,4);

        System.out.println(list.stream().reduce(0,(a,b)-> a+b));
    }
}
