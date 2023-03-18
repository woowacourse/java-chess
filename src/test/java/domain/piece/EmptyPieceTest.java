package domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyPieceTest {

    @DisplayName("움직일 수 있는지 확인하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCallIsMovable() {
        assertThatThrownBy(() ->
                new EmptyPiece().isMovable(Pawn.createOfBlack(), Position.of("a", "5"), Position.of("c", "7")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("서버 내부 에러 - EmptyPiece는 움직임을 확인할 수 없습니다.");
    }

    @DisplayName("움직임의 경로를 요청하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenCallGetPath() {
        assertThatThrownBy(() ->
                new EmptyPiece().collectPath(Position.of("a", "5"), Position.of("c", "7")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("서버 내부 에러 - EmptyPiece는 움직임의 경로를 계산할 수 없습니다.");
    }
}
