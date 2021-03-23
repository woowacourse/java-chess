package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.PlayGame;
import chess.domain.board.Pieces;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractStateTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = new Ready();
        state = state.init();
    }

    @Test
    @DisplayName("컬러가 같은지 테스트")
    void isSameColor() {
        Piece piece = new Queen(Color.WHITE, Position.of("a1"));
        assertThat(new Play(Color.WHITE, new Pieces()).isSameColor(piece)).isTrue();

        piece = new Queen(Color.BLACK, Position.of("a1"));
        assertThat(new Play(Color.WHITE, new Pieces()).isSameColor(piece)).isFalse();
    }

    @Test
    @DisplayName("점수 테스트")
    void score() {
        assertThat(state.score(Color.BLACK)).isEqualTo(38.0);
        assertThat(state.score(Color.WHITE)).isEqualTo(38.0);

        state = PlayGame.killKingOfBlack(state);

        assertThat(state.score(Color.BLACK)).isEqualTo(36.0);
        assertThat(state.score(Color.WHITE)).isEqualTo(37.0);

        state = new Ready().init();
        state = PlayGame.killKingOfWhite(state);

        assertThat(state.score(Color.BLACK)).isEqualTo(37.0);
        assertThat(state.score(Color.WHITE)).isEqualTo(36.0);
    }

    @Test
    @DisplayName("우승자 확인 테스트")
    void winner() {
        state = PlayGame.killKingOfBlack(state);
        assertThat(state.winner()).isEqualTo(Color.WHITE);

        state = new Ready().init();
        state = PlayGame.killKingOfWhite(state);
        assertThat(state.winner()).isEqualTo(Color.BLACK);
    }


}