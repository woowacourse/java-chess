package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class BishopTest {
    /*
    ........  8
    ........  7
    ..x.....  6
    .....x..  5
    ....b...  4
    ........  3
    ......x.  2
    .x......  1
    abcdefgh
    */
    @DisplayName("성공 : 비숍은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = E4;

        assertAll(
                () -> assertThat(testBishop.canMove(startPosition, B1, NullPiece.getInstance())).isTrue(),
                () -> assertThat(testBishop.canMove(startPosition, G2, NullPiece.getInstance())).isTrue(),
                () -> assertThat(testBishop.canMove(startPosition, F5, NullPiece.getInstance())).isTrue(),
                () -> assertThat(testBishop.canMove(startPosition, C6, NullPiece.getInstance())).isTrue()
        );
    }


    /*
    ........  8
    ...x....  7
    ........  6
    ........  5
    x..b..x.  4
    ........  3
    ........  2
    ...x....  1
    abcdefgh
    */
    @DisplayName("실패 : 비숍은 대각선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonal_From_StartPosition() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = D4;

        assertAll(
                () -> Assertions.assertThat(testBishop.canMove(startPosition, A4, NullPiece.getInstance())).isFalse(),
                () -> Assertions.assertThat(testBishop.canMove(startPosition, G4, NullPiece.getInstance())).isFalse(),
                () -> Assertions.assertThat(testBishop.canMove(startPosition, D7, NullPiece.getInstance())).isFalse(),
                () -> Assertions.assertThat(testBishop.canMove(startPosition, D1, NullPiece.getInstance())).isFalse()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ....b...  4
    ...p....  3
    ........  2
    .x......  1
    abcdefgh
    */
    @DisplayName("비숍은 대각선 경유경로에 다른 기물이 있다면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_OtherPieceInDiagonalPath() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = D4;

        assertThat(testBishop.canMove(startPosition, B1, NullPiece.getInstance())).isFalse();
    }
}
