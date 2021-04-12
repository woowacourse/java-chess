package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.PlayGame;
import chess.domain.piece.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    private State play;

    @BeforeEach
    void setUp() {
        play = new Ready().init();
    }

    @Test
    @DisplayName("끝났는지 상태 테스트")
    void isFinish() {
        assertThat(play.isFinish()).isFalse();
    }

    @Test
    @DisplayName("우승자 확인 테스트")
    void winner() {
        assertThat(play.winner()).isNull();
    }

    @Test
    @DisplayName("초기화기 에러가 나는지 테스트")
    void init() {
        assertThatThrownBy(play::init).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 플레이시 에러가 안나는지 확인 테스트")
    void movePiece() {
        assertThatCode(() -> play.movePiece(Position.of("b2"), Position.of("b4")))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임이 끝났을때 Finish 를 반환하는지 테스트")
    void finish() {
        assertThat(PlayGame.killKingOfBlack(play)).isInstanceOf(Finish.class);
    }
}