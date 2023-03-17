package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {

    @Test
    @DisplayName("빈 공간 선택 시 항상 움직일 수 없다.")
    void movableUnsupportedExceptionTest() {
        Piece empty = new Empty();
        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.THREE);

        PieceMove result = empty.getMovement(from, to);

        assertThat(result).isInstanceOf(InvalidMove.class);
    }
}
