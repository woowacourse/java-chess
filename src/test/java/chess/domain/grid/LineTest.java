package chess.domain.grid;

import chess.domain.Line;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LineTest {
    @Test
    @DisplayName("체스 보드의 한 줄을 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            List<Piece> pieces = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                char x = (char)('a' + i);
                pieces.add(new Empty(x, '1'));
            }
            new Line(pieces);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("같은 포지션을 가진 Piece가 있을 경우 예외 발생")
    public void validateDuplicatedPieces_ThrowsException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            List<Piece> pieces = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                char x = (char)('a' + i);
                pieces.add(new Empty(x, '1'));
            }
            pieces.add(new Empty('a', '1'));
            new Line(pieces);
        }).withMessage("같은 포지션을 가진 체스 말이 존재할 수 없습니다.");
    }
}
