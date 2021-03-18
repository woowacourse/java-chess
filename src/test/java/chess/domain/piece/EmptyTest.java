package chess.domain.piece;

import chess.domain.piece.team.Neutral;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyTest {

    @Test
    @DisplayName("빈말 테스트")
    void createTest(){
        assertThat(new Empty(new Neutral(Symbol.EMPTY))).isInstanceOf(Empty.class);
    }
}
