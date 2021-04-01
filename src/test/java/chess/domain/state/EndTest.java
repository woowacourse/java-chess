package chess.domain.state;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EndTest {
    private State end;

    @BeforeEach
    void setUp() {
        end = new End(new Pieces(PieceFactory.initialPieces()), Color.NONE);
    }

    @DisplayName("시작 전 상태인지 확인한다.")
    @Test
    void 시작_전_상태인지_확인() {
        boolean isReady = end.isReady();

        assertThat(isReady).isFalse();
    }

    @DisplayName("끝난 상태인지 확인한다.")
    @Test
    void 끝난_상태인지_확인() {
        boolean isFinish = end.isFinish();

        assertThat(isFinish).isTrue();
    }

    @DisplayName("게임을 시작한다 - 이미 끝남 예외")
    @Test
    void 게임시작() {
        assertThatThrownBy(() -> end.start())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임을 끝낸다 - 이미 끝남 예외")
    @Test
    void 게임끝() {
        assertThatThrownBy(() -> end.end())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
