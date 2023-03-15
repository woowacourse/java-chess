package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TraceTest {

    @Nested
    class 기록추가 {
        @Test
        void should_새_포지션_정보가_추가된다_when_add메서드를_호출하면() {
            //given
            Trace trace = new Trace();

            //when
            trace.add(1, new Position(Rank.A, File.TWO));

            //then
            assertThat(trace).extracting("logs", InstanceOfAssertFactories.collection(Position.class))
                    .hasSize(1);
        }
    }


    @Nested
    class 기록확인 {
        @Test
        void should_true반환_when_움직인기록이있다면() {
            //given
            Trace trace = new Trace();
            trace.add(1, new Position(Rank.A, File.TWO));

            //when
            boolean actual = trace.hasLog();

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void should_false반환_when_움직인기록이없다면() {
            //given
            Trace trace = new Trace();

            //when
            boolean actual = trace.hasLog();

            //then
            assertThat(actual).isFalse();
        }
    }
}
