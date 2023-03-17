package techcourse.fp.mission;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class CarTest {

    @Test
    public void 이동() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(new MoveStrategy() {
            @Override
            public boolean isMovable() {
                return true;
            }
        });
        assertThat(actual).isEqualTo(new Car("pobi", 1));
    }

    @Test
    public void 이동_람다() {
        Car car = new Car("pobi", 0);
        MoveStrategy moveStrategy = () -> true;
        Car actual = car.move(moveStrategy);
        assertThat(actual).isEqualTo(new Car("pobi", 1));
    }


    @Test
    public void 정지() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(new MoveStrategy() {
            @Override
            public boolean isMovable() {
                return false;
            }
        });
        assertThat(actual).isEqualTo(new Car("pobi", 0));
    }

    @Test
    public void 정지_람다() {
        Car car = new Car("pobi", 0);
        MoveStrategy stopStrategy = () -> false;
        Car actual = car.move(stopStrategy);
        assertThat(actual).isEqualTo(new Car("pobi", 0));
    }
}
