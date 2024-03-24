package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {
    @Test
    @DisplayName("경로 찾기를 시도하면 빈 배열을 반환한다.")
    void findPath() {
        //given
        Empty empty = new Empty();
        ChessPosition sourcePosition = new ChessPosition(File.F, Rank.TWO);
        ChessPosition targetPosition = new ChessPosition(File.F, Rank.FOUR);
        Piece targetPiece = new Empty();

        //when
        List<ChessPosition> result = empty.findPath(sourcePosition, targetPosition, targetPiece);

        //then
        assertThat(result).isEmpty();
    }
}
