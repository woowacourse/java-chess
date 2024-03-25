package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.B2;
import static chess.fixture.PositionFixtures.B3;
import static chess.fixture.PositionFixtures.H1;
import static chess.fixture.PositionFixtures.H2;
import static chess.fixture.PositionFixtures.H8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("퀸은 북쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtNorth() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(A1, H8)).isFalse();
    }

    @DisplayName("퀸은 남쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtSouth() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(H8, A1)).isFalse();
    }

    @DisplayName("퀸은 동쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtEast() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(A1, H1)).isFalse();
    }

    @DisplayName("퀸은 서쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtWest() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(H1, A1)).isFalse();
    }

    @DisplayName("퀸은 북동쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtNortheast() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(A1, H8)).isFalse();
    }

    @DisplayName("퀸은 북서쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtNorthwest() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(H8, A1)).isFalse();
    }

    @DisplayName("퀸은 남동쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtSoutheast() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(A1, H8)).isFalse();
    }

    @DisplayName("퀸은 남서쪽 방향으로 이동할 수 있다")
    @Test
    void should_QueenCanMove_When_DestinationAtSouthwest() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(H8, A1)).isFalse();
    }

    @DisplayName("퀸은 대각선 혹은 직선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonalAndIsNotStraight_From_StartPosition() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canNotMoveByItsOwnInPassing(A1, B3)).isTrue();
    }

    @DisplayName("퀸은 중간에 기물이 있는 경우 이동할 수 없다")
    @Test
    void should_QueenCanNotMove_When_ObstacleIsOnPath() {
        Queen queen = new Queen(Team.WHITE);
        Pawn obstacle = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, queen);
        board.put(B2, obstacle);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(queen.canMove(A1, H2, chessBoard)).isFalse();
    }

    @DisplayName("퀸은 시작 위치와 같은 위치로 이동할 수 없다")
    @Test
    void should_QueenCanNotMove_When_DestinationIsSameWithStart() {
        Queen queen = new Queen(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, queen);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(queen.canMove(A1, A1, chessBoard)).isFalse();
    }

    @DisplayName("퀸은 도착 위치에 같은 팀 기물이 있으면 이동할 수 없다")
    @Test
    void should_QueenCanNotMove_When_FriendlyPieceAtDestination() {
        Queen queen = new Queen(Team.WHITE);
        Pawn friendlyPiece = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, queen);
        board.put(H8, friendlyPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(queen.canMove(A1, H8, chessBoard)).isFalse();
    }
}
