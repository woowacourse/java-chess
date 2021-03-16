package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PositionTest {
    
    @ParameterizedTest(name = "포지션 생성 테스트")
    @ValueSource(ints = {0, 7})
    void initPositionTest(int position) {
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> Position.from(position);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @ParameterizedTest(name = "인덱스를 벗어날 경우 예외 발생 테스트")
    @ValueSource(ints = {-1, 8})
    void initPositionTest_OutOfBounds_ExceptionThrown(int position) {
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> Position.from(position);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("인덱스는 0이상 7이하이어야 합니다.");
    }
}