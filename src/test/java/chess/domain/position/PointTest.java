package chess.domain.position;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PointTest {

    @ParameterizedTest(name = "생성 테스트")
    @ValueSource(ints = {0, 7})
    void initPositionTest(int point) {

        // when
        ThrowableAssert.ThrowingCallable callable = () -> Point.from(point);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "인덱스를 벗어날 경우 예외 발생 테스트")
    @ValueSource(ints = {-1, 8})
    void initPositionTest_OutOfBounds_ExceptionThrown(int point) {

        // when
        ThrowableAssert.ThrowingCallable callable = () -> Point.from(point);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("인덱스는 0이상 7이하이어야 합니다.");
    }
}
