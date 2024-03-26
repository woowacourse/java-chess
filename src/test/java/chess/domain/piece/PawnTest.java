package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PawnTest {

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ...x....  4
    ...p....  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("흰색 폰은 한칸 앞에 아무 기물이 없다면 위쪽으로 전진할 수 있다")
    @Test
    void should_WhitePawnCanForwardToUpPosition_When_NoOtherPieceInDestination() {
        Pawn whitePawn = Pawn.whitePawn();
        Position startPosition = D3;

        assertThat(whitePawn.canMove(startPosition, D4, NullPiece.getInstance())).isTrue();
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ...P....  3
    ...x....  2
    ........  1
    abcdefgh
    */
    @DisplayName("검은색 폰은 한칸 앞에 아무 기물이 없다면 아래쪽으로 전진할 수 있다")
    @Test
    void should_BlackPawnCanForwardToDownPosition_When_NoOtherPieceInDestination() {
        Pawn blackPawn = Pawn.blackPawn();
        Position startPosition = D3;

        assertThat(blackPawn.canMove(startPosition, D2, NullPiece.getInstance())).isTrue();
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ...p....  3
    ...x....  2
    ........  1
    abcdefgh
    */
    @DisplayName("흰색 폰은 아래쪽으로 전진할 수 없다")
    @Test
    void should_WhitePawnCanNotForwardToDownPosition() {
        Pawn blackPawn = Pawn.whitePawn();
        Position startPosition = D3;

        assertThat(blackPawn.canMove(startPosition, D2, NullPiece.getInstance())).isFalse();
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ...x....  4
    ...P....  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("검은색 폰은 위쪽으로 전진할 수 없다")
    @Test
    void should_BlackPawnCanNotForwardToDownPosition() {
        Pawn blackPawn = Pawn.blackPawn();
        Position startPosition = D3;

        assertThat(blackPawn.canMove(startPosition, D4, NullPiece.getInstance())).isFalse();
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ...x....  4
    ...x....  3
    ...p....  2
    ........  1
    abcdefgh
    */
    @DisplayName("흰색 폰이 첫 위치에 있다면 한칸 혹은 두칸 전진할 수 있다")
    @Test
    void should_WhitePawnCanForwardOneOrTwoSquare_When_FirstMove() {
        Pawn whitePawn = Pawn.whitePawn();
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, D3, NullPiece.getInstance())).isTrue(),
                () -> assertThat(whitePawn.canMove(startPosition, D4, NullPiece.getInstance())).isTrue()
        );
    }

    /*
    ........  8
    ...P....  7
    ...x....  6
    ...x....  5
    ........  4
    ........  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("검은색 폰이 첫 위치에 있다면 한칸 혹은 두칸 전진할 수 있다")
    @Test
    void should_BlackPawnCanForwardOneOrTwoSquare_When_FirstMove() {
        Pawn blackPawn = Pawn.blackPawn();
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, D6, NullPiece.getInstance())).isTrue(),
                () -> assertThat(blackPawn.canMove(startPosition, D5, NullPiece.getInstance())).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ..K.Q...  3
    ...p....  2
    ........  1
    abcdefgh
    */
    @DisplayName("흰색 폰의 한칸 대각선 경로에 다른 팀의 기물이 있다면 이동할 수 있다")
    @Test
    void should_WhitePawnCanForward_When_OtherTeamPiece_In_OneDiagonalSquare() {
        Pawn whitePawn = Pawn.whitePawn();
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, C3, new King(Team.BLACK))).isTrue(),
                () -> assertThat(whitePawn.canMove(startPosition, E3, new Queen(Team.BLACK))).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ..k.q...  3
    ...p....  2
    ........  1
    abcdefgh
    */
    @DisplayName("흰색 폰의 한칸 대각선 경로에 같은 팀의 기물이 있다면 이동할 수 없다")
    @Test
    void should_WhitePawnCanNotForward_When_SameTeamPiece_In_OneDiagonalSquare() {
        Pawn whitePawn = Pawn.whitePawn();
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, C3, new King(Team.WHITE))).isFalse(),
                () -> assertThat(whitePawn.canMove(startPosition, E3, new Queen(Team.WHITE))).isFalse()
        );
    }

    /*
    ........  8
    ...P....  7
    ..k.q...  6
    ........  5
    ........  4
    ........  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("검은색 폰의 한칸 대각선 경로에 다른 팀의 기물이 있다면 이동할 수 있다")
    @Test
    void should_BlackPawnCanForward_When_OtherTeamPiece_In_OneDiagonalSquare() {
        Pawn blackPawn = Pawn.blackPawn();
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, C6, new King(Team.WHITE))).isTrue(),
                () -> assertThat(blackPawn.canMove(startPosition, E6, new Queen(Team.WHITE))).isTrue()
        );
    }

    /*
    ........  8
    ...P....  7
    ..K.Q...  6
    ........  5
    ........  4
    ........  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("검은색 폰의 한칸 대각선 경로에 같은 팀의 기물이 있다면 이동할 수 없다")
    @Test
    void should_BlackPawnCanNotForward_When_SameTeamPiece_In_OneDiagonalSquare() {
        Pawn blackPawn = Pawn.blackPawn();
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, C6, new King(Team.BLACK))).isFalse(),
                () -> assertThat(blackPawn.canMove(startPosition, E6, new Queen(Team.BLACK))).isFalse()
        );
    }
}
