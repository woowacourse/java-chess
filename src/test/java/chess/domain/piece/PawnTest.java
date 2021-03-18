package chess.domain.piece;

import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰 생성 테스트")
    void createTest(){
        assertThat(new Pawn(new Black(Symbol.PAWN))).isInstanceOf(Pawn.class);
        assertThat(new Pawn(new White(Symbol.PAWN))).isInstanceOf(Pawn.class);
    }
}