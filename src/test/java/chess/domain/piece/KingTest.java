package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Team;
import chess.domain.piece.team.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

    @Test
    @DisplayName("킹 생성 테스트")
    void createTest(){
        assertThat(new King(new Black(Symbol.KING))).isInstanceOf(King.class);
        assertThat(new King(new White(Symbol.KING))).isInstanceOf(King.class);
    }
}