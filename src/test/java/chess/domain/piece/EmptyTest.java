package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Direction;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {
    @DisplayName("Empty Piece는 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_EmptyMove() {
        final Piece empty = new Empty();
        final Square source = new Square(File.A, Rank.THREE);
        final Square target = new Square(File.A, Rank.FOUR);

        assertThatThrownBy(() -> empty.isMovable(source, target, Direction.UP))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }
}
