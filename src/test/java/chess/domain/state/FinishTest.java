package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishTest {

    private final State finish = new Finish(Color.WHITE, new Pieces());

    @Test
    @DisplayName("끝났는지 상태 테스트")
    void isFinish() {
        assertThat(finish.isFinish()).isTrue();
    }

    @Test
    @DisplayName("우승자 확인 테스트")
    void winner() {
        assertThat(finish.winner()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("초기화기 에러가 나는지 테스트")
    void init() {
        assertThatThrownBy(finish::init).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("말 이동시 에러가 나는지 테스트")
    void movePiece() {
        assertThatThrownBy(() -> finish.movePiece(Position.of("a1"), Position.of("a2")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}