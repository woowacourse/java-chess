package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private static final State READY = new Ready();

    @Test
    @DisplayName("Play 가 나오는지 테스트")
    void init() {
        assertThat(READY.init()).isInstanceOf(Play.class);
    }

    @Test
    @DisplayName("우승자 확인 테스트 ")
    void winner() {
        assertThat(READY.winner()).isNull();
    }

    @Test
    @DisplayName("말 이동시 에러가 나는지 테스트")
    void movePiece() {
        assertThatThrownBy(() -> READY.movePiece(Position.of("a1"), Position.of("a2")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}