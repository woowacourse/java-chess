package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @ValueSource(strings={"a","b","c","d","e","f","g","h"})
    void should_정상생성_when_a에서h가_입력되었을때(final String input) {
        //given

        //when

        //then
        assertThat(Rank.from(input)).isInstanceOf(Rank.class);
    }

    @ParameterizedTest
    @ValueSource(strings={"", " ", "i","0","z","aa","오"})
    void should_예외를던진다_when_a에서h외의값이_입력됐을때(final String input) {
        //given


        //when
        final ThrowingCallable throwingCallable = () -> Rank.from(input);

        //then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank는 a에서 h사이의 값 이어야 합니다.");
    }
}