package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QueenTest {

    /*
    ........  8
    ........  7
    ..x.....  6
    .....x..  5
    ....q...  4
    ........  3
    .......x  2
    .x......  1
    abcdefgh
    */
    @DisplayName("성공 : 퀸은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = E4;

        assertAll(
                () -> assertThat(testQueen.canMove(startPosition, B1, board)).isTrue(),
                () -> assertThat(testQueen.canMove(startPosition, G2, board)).isTrue(),
                () -> assertThat(testQueen.canMove(startPosition, F5, board)).isTrue(),
                () -> assertThat(testQueen.canMove(startPosition, C6, board)).isTrue()
        );
    }

    /*
    ........  8
    ...x....  7
    ........  6
    ........  5
    x..q..x.  4
    ........  3
    ........  2
    ...x....  1
   abcdefgh
    */
    @DisplayName("성공 : 퀸은 직선관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsStraight_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = D4;

        assertAll(
                () -> Assertions.assertThat(testQueen.canMove(startPosition, A4, board)).isTrue(),
                () -> Assertions.assertThat(testQueen.canMove(startPosition, G4, board)).isTrue(),
                () -> Assertions.assertThat(testQueen.canMove(startPosition, D7, board)).isTrue(),
                () -> Assertions.assertThat(testQueen.canMove(startPosition, D1, board)).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    x.......  6
    .......x  5
    ...q....  4
    .x......  3
    .......x  2
    ........  1
    abcdefgh
    */
    @DisplayName("실패 : 퀸은 대각선 혹은 직선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonalAndIsNotStraight_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = D4;

        assertAll(
                () -> Assertions.assertThat(testQueen.canMove(startPosition, A6, board)).isFalse(),
                () -> Assertions.assertThat(testQueen.canMove(startPosition, B3, board)).isFalse(),
                () -> Assertions.assertThat(testQueen.canMove(startPosition, H2, board)).isFalse(),
                () -> Assertions.assertThat(testQueen.canMove(startPosition, H5, board)).isFalse()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ....q...  4
    ...p....  3
    ........  2
    .x......  1
    abcdefgh
    */
    @DisplayName("퀸은 대각선 경유경로에 다른 기물이 있다면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_OtherPieceInDiagonalPath(){
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D3, new Pawn(Team.BLACK))));
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = D4;

        assertThat(testQueen.canMove(startPosition,B1, board)).isFalse();
    }

    /*
   ........  8
   ........  7
   ........  6
   ........  5
   ...q....  4
   ...p....  3
   ........  2
   ...x....  1
   abcdefgh
   */
    @DisplayName("룩은 직선 경유경로에 다른 기물이 있다면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_OtherPieceInStraightPath(){
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D3, new Pawn(Team.BLACK))));
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = D4;

        assertThat(testQueen.canMove(startPosition,D1, board)).isFalse();
    }
}
