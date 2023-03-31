package chess.domain.piece.info;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Turn;
import chess.domain.position.Rank;
import chess.domain.position.File;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TraceTest {

    @Nested
    class Log추가 {

        @Test
        void add메서드를_호출하면_새_포지션_정보가_추가된다() {
            //given
            Trace trace = new Trace();

            //when
            trace.add(new Turn(), Position.of(File.A, Rank.TWO));

            //then
            assertThat(trace).extracting("logs",
                    InstanceOfAssertFactories.collection(Position.class))
                .hasSize(1);
        }
    }


    @Nested
    class Log조회 {

        @Test
        void 기물을_움직인_Log가_있다면_true반환() {
            //given
            Trace trace = new Trace();
            trace.add(new Turn(), Position.of(File.A, Rank.TWO));

            //when
            boolean actual = trace.hasLog();

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void 기물을_움직인_Log가_없다면_false반환() {
            //given
            Trace trace = new Trace();

            //when
            boolean actual = trace.hasLog();

            //then
            assertThat(actual).isFalse();
        }
    }
}
