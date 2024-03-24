package chess.model.piece;

import static chess.model.Fixture.F2;
import static chess.model.Fixture.F4;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.ChessPosition;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {
    @Test
    @DisplayName("경로 찾기를 시도하면 빈 배열을 반환한다.")
    void findPath() {
        //given
        Piece sourcePiece = new Empty();
        Piece targetPiece = new Empty();

        //when
        List<ChessPosition> result = sourcePiece.findPath(F2, F4, targetPiece);

        //then
        assertThat(result).isEmpty();
    }
}
