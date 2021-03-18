package chess.domain.piece;

import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩 생성 테스트")
    void createTest(){
        assertThat(new Rook(new Black(Symbol.ROOK))).isInstanceOf(Rook.class);
        assertThat(new Rook(new White(Symbol.ROOK))).isInstanceOf(Rook.class);
    }
}