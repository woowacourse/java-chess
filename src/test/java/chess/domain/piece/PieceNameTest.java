package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceNameTest {
    @Test
    @DisplayName("체스 말의 이름으로 제대로 된 클래스 이름을 반환하는 지 체크")
    public void getClassNameByPieceName() throws ClassNotFoundException {
        String pieceClassName = PieceName.getClassNameByPieceName('p');
        assertThat(Class.forName(pieceClassName)).isEqualTo(Pawn.class);
    }
}
