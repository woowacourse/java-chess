package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.api.BeforeEach;
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
        }).withMessage("같은 포지션을 가진 체스 말이 존재할 수 없습니다.");
    }
//    @Test
//    @DisplayName("같은 포지션을 가진 Piece가 있을 수 없다.")
//    public void init_
//
//    set<Position> ~~~~~
//    List<Piece> ~~ for loopp ~~~~

}
