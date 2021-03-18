package chess.domain.piece;

import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트 생성 테스트")
    void createKnightTest() {
        assertThat(new Knight(new Black(Symbol.KNIGHT))).isInstanceOf(Knight.class);
        assertThat(new Knight(new White(Symbol.KNIGHT))).isInstanceOf(Knight.class);
    }
}