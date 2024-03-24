package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        ChessBoard board = new ChessBoard(new HashMap<>());
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position startPosition = D3;

        assertThat(whitePawn.canMove(startPosition, D4, board)).isTrue();
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
        ChessBoard board = new ChessBoard(new HashMap<>());
        Pawn blackPawn = new Pawn(Team.BLACK);
        Position startPosition = D3;

        assertThat(blackPawn.canMove(startPosition, D2, board)).isTrue();
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
        ChessBoard board = new ChessBoard(new HashMap<>());
        Pawn blackPawn = new Pawn(Team.WHITE);
        Position startPosition = D3;

        assertThat(blackPawn.canMove(startPosition, D2, board)).isFalse();
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
        ChessBoard board = new ChessBoard(new HashMap<>());
        Pawn blackPawn = new Pawn(Team.BLACK);
        Position startPosition = D3;

        assertThat(blackPawn.canMove(startPosition, D4, board)).isFalse();
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
        ChessBoard board = new ChessBoard(new HashMap<>());
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, D3, board)).isTrue(),
                () -> assertThat(whitePawn.canMove(startPosition, D4, board)).isTrue()
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
        ChessBoard board = new ChessBoard(new HashMap<>());
        Pawn blackPawn = new Pawn(Team.BLACK);
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, D6, board)).isTrue(),
                () -> assertThat(blackPawn.canMove(startPosition, D5, board)).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ...x....  4
    ...P....  3
    ...p....  2
    ........  1
    abcdefgh
    */
    @DisplayName("흰색 폰의 전진경로에 다른 기물이 있다면 이동하지 못한다")
    @Test
    void should_WhitePawnCanNotForward_When_OtherPieceInPath() {
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D3, new Pawn(Team.BLACK))));
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, D3, board)).isFalse(),
                () -> assertThat(whitePawn.canMove(startPosition, D4, board)).isFalse()
        );
    }

    /*
    ........  8
    ...P....  7
    ...p....  6
    ...x....  5
    ........  4
    ........  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("검은색 폰의 전진경로에 다른 기물이 있다면 이동하지 못한다")
    @Test
    void should_BlackPawnCanNotForward_When_OtherPieceInPath() {
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D6, new Pawn(Team.WHITE))));
        Pawn blackPawn = new Pawn(Team.BLACK);
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, D6, board)).isFalse(),
                () -> assertThat(blackPawn.canMove(startPosition, D5, board)).isFalse()
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
        ChessBoard board = new ChessBoard(Map.ofEntries(
                Map.entry(C3, new King(Team.BLACK)),
                Map.entry(E3, new Queen(Team.BLACK))));
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, C3, board)).isTrue(),
                () -> assertThat(whitePawn.canMove(startPosition, E3, board)).isTrue()
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
        ChessBoard board = new ChessBoard(Map.ofEntries(
                Map.entry(C3, new King(Team.WHITE)),
                Map.entry(E3, new Queen(Team.WHITE))));
        Pawn whitePawn = new Pawn(Team.WHITE);
        Position startPosition = D2;

        assertAll(
                () -> assertThat(whitePawn.canMove(startPosition, C3, board)).isFalse(),
                () -> assertThat(whitePawn.canMove(startPosition, E3, board)).isFalse()
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
        ChessBoard board = new ChessBoard(Map.ofEntries(
                Map.entry(C6, new King(Team.WHITE)),
                Map.entry(E6, new Queen(Team.WHITE))));
        Pawn blackPawn = new Pawn(Team.BLACK);
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, C6, board)).isTrue(),
                () -> assertThat(blackPawn.canMove(startPosition, E6, board)).isTrue()
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
        ChessBoard board = new ChessBoard(Map.ofEntries(
                Map.entry(C6, new King(Team.BLACK)),
                Map.entry(E6, new Queen(Team.BLACK))));
        Pawn blackPawn = new Pawn(Team.BLACK);
        Position startPosition = D7;

        assertAll(
                () -> assertThat(blackPawn.canMove(startPosition, C6, board)).isFalse(),
                () -> assertThat(blackPawn.canMove(startPosition, E6, board)).isFalse()
        );
    }
}
