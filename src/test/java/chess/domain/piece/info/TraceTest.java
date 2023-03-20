package chess.domain.piece.info;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Turn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
            trace.add(new Turn(), Position.of(Rank.A, File.TWO));

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
            trace.add(new Turn(), Position.of(Rank.A, File.TWO));

            //when
            boolean actual = trace.isEmpty();

            //then
            assertThat(actual).isFalse();
        }

        @Test
        void should_false반환_when_움직인기록이없다면() {
            //given
            Trace trace = new Trace();

            //when
            boolean actual = trace.isEmpty();

            //then
            assertThat(actual).isTrue();
        }
    }
}
