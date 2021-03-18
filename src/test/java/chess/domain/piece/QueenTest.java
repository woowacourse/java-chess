package chess.domain.piece;

import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    @DisplayName("퀸 생성 테스트")
    void createTest(){
        assertThat(new Queen(new Black(Symbol.QUEEN))).isInstanceOf(Queen.class);
        assertThat(new Queen(new White(Symbol.QUEEN))).isInstanceOf(Queen.class);
    }
}