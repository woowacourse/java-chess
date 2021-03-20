package chess.domain.grid;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {
    @Test
    @DisplayName("Line에 Piece를 할당하는 지 테스트")
    public void assignPiece() {
        Line line = Line.empty(1);
        Pawn pawn = new Pawn(Color.BLACK, 'b', '1');
        line.assignPiece('b', pawn);
        assertThat(line.piece('b')).isEqualTo(pawn);
    }

    @Test
    @DisplayName("Line에서 xPosition으로 해당하는 Piece를 제대로 찾는 지 테스트")
    public void piece() {
        Line line = Line.general(1, Color.WHITE);
        assertThat(line.piece('a')).isEqualTo(line.pieces().get(0));
    }

}
