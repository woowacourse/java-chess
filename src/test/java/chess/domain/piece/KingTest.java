package chess.domain.piece;

import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("킹 생성 테스트")
    void createTest(){
        assertThat(new King(new Black(Symbol.KING))).isInstanceOf(King.class);
        assertThat(new King(new White(Symbol.KING))).isInstanceOf(King.class);
    }
}