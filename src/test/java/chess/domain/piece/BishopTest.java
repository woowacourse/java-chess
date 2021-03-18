package chess.domain.piece;

import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍 생성 테스트")
    void createTest(){
        assertThat(new Bishop(new Black(Symbol.BISHOP))).isInstanceOf(Bishop.class);
        assertThat(new Bishop(new White(Symbol.BISHOP))).isInstanceOf(Bishop.class);
    }
}