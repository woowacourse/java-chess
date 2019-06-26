package chess.domain.pieces;

import chess.domain.ChessTeam;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    void 같은_팀인지_테스트(){
        Bishop bishop1 = new Bishop(ChessTeam.BLACK);
        Bishop bishop2 = new Bishop(ChessTeam.BLACK);
        assertThat(bishop1.isAlly(bishop2)).isTrue();
    }
}
