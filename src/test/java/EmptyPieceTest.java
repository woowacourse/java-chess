import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmptyPieceTest {

    @DisplayName("빈 말의 경우 isMovable호출 시 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCallIsMovable() {
        assertThatThrownBy(() ->
                new EmptyPiece().isMovable(Pawn.createOfBlack(), Position.of("a", "5"), Position.of("c", "7")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("지원하지 않는 메서드 입니다.");
    }

    @DisplayName("빈 말의 경우 getPath호출 시 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCallGetPath() {
        assertThatThrownBy(() ->
                new EmptyPiece().collectPath(Position.of("a", "5"), Position.of("c", "7")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("지원하지 않는 메서드 입니다.");
    }
}