package chess.domain.grid;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.exception.ChessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LineTest {
    @Test
    @DisplayName("Line에 Piece를 할당하는 지 테스트")
    public void assignPiece() {
        Line line = Line.empty(Row.FIRST);
        Pawn pawn = new Pawn(Color.BLACK, 'b', '1');
        line.assignPiece('b', pawn);
        assertThat(line.piece('b')).isEqualTo(pawn);
    }

    @Test
    @DisplayName("Line에서 xPosition으로 해당하는 Piece를 제대로 찾는 지 테스트")
    public void piece() {
        Line line = Line.general(Row.FIRST, Color.WHITE);
        assertThat(line.piece('a')).isEqualTo(line.pieces().get(0));
    }

    @Test
    @DisplayName("올바르지 않은 xPosition을 입력하는 경우 예외 발생")
    public void piece_ThrowException() {
        Line line = Line.general(Row.FIRST, Color.BLACK);
        assertThatThrownBy(() -> {
            line.piece('l');
        }).isInstanceOf(ChessException.class).hasMessage("해당하는 이름의 Row가 존재하지 않습니다.");
    }

}
