package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"너무긴이름입니다", "짧"})
    @EmptySource
    void should_예외를던진다_when_올바르지않은이름이입력됐을때(String rawName) {
        //given

        //when
        final ThrowingCallable throwingCallable = () -> new Name(rawName);

        //then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 2~5자만 가능합니다.");
    }

}