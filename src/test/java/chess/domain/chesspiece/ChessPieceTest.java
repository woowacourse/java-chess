package chess.domain.chesspiece;

import chess.domain.game.Team;
import chess.domain.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceTest {
    @Test
    @DisplayName("ChessPiece 생성 테스트")
    void create() {
        assertThat(new ChessPiece("k", new Position(1, 'a'), Team.BLACK)).isInstanceOf(ChessPiece.class);
    }
}
