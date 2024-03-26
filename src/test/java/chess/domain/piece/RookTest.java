package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RookTest {
    /*
    ........  8
    ...x....  7
    ........  6
    ........  5
    x..r..x.  4
    ........  3
    ........  2
    ...x....  1
   abcdefgh
    */
    @DisplayName("성공 : 룩은 직선관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsStraight_From_StartPosition() {
        Rook testRook = new Rook(Team.WHITE);
        Position startPosition = D4;

        assertAll(
                () -> Assertions.assertThat(testRook.canMove(startPosition, A4, NullPiece.getInstance())).isTrue(),
                () -> Assertions.assertThat(testRook.canMove(startPosition, G4, NullPiece.getInstance())).isTrue(),
                () -> Assertions.assertThat(testRook.canMove(startPosition, D7, NullPiece.getInstance())).isTrue(),
                () -> Assertions.assertThat(testRook.canMove(startPosition, D1, NullPiece.getInstance())).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    ..x.....  6
    .....x..  5
    ....r...  4
    ........  3
    .......x  2
    .x......  1
    abcdefgh
    */
    @DisplayName("실패 : 룩은 직선관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotStraight_From_StartPosition() {
        Rook testRook = new Rook(Team.WHITE);
        Position startPosition = E4;

        assertAll(
                () -> assertThat(testRook.canMove(startPosition, B1, NullPiece.getInstance())).isFalse(),
                () -> assertThat(testRook.canMove(startPosition, G2, NullPiece.getInstance())).isFalse(),
                () -> assertThat(testRook.canMove(startPosition, F5, NullPiece.getInstance())).isFalse(),
                () -> assertThat(testRook.canMove(startPosition, C6, NullPiece.getInstance())).isFalse()
        );
    }
}
