package chess.domain.game;

import chess.domain.util.WrongOperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperationTypeTest {

    @DisplayName("of 스태틱 생성 메서드 정상 동작 테스트")
    @Test
    void of_static_constructor_method_normal_test() {
        assertThat(OperationType.of("end")).isEqualTo(OperationType.END);
    }

    @DisplayName("of 스태틱 생성 메서드 유효하지 않은 입력시 예외처리")
    @Test
    void of_static_constructor_method_exception_test() {
        assertThatThrownBy(() -> OperationType.of("toney"))
                .isInstanceOf(WrongOperationException.class)
                .hasMessage("수행할 수 없는 명령입니다.");
    }

    @DisplayName("checkFirstOperations start나 end 외의 OperationType에서 메서드 사용시 예외처리 발생")
    @Test
    void checkFirstOperations_not_start_and_not_end_throw_exception() {

        assertThatThrownBy(OperationType.STATUS::checkFirstOperations)
                .isInstanceOf(WrongOperationException.class)
                .hasMessage("수행할 수 없는 명령입니다.");
    }
}