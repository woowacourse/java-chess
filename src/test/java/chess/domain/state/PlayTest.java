package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    private static final State play = new Play(Color.WHITE);

    @Test
    @DisplayName("다음상태 전환 테스트")
    void nextState() {
        assertThat(play.nextState()).isInstanceOf(Finish.class);
    }

    @Test
    @DisplayName("끝났는지 상태 테스트")
    void isFinish() {
        assertThat(play.isFinish()).isFalse();
    }

    @Test
    @DisplayName("다음 턴으로 넘기기 테스트")
    void nextTurn() {
        assertThatCode(play::nextTurn).doesNotThrowAnyException();

        Piece piece = new Queen(Color.BLACK, Position.of("a1"));
        assertThat(play.isSameColor(piece)).isTrue();
    }

    @Test
    @DisplayName("우승자 확인 테스트")
    void winner() {
        assertThat(play.winner()).isEqualTo(Color.BLANK);
    }

    @Test
    @DisplayName("이동여부 테스트")
    void validateMove() {
        assertThatCode(play::validateMove).doesNotThrowAnyException();
    }
}