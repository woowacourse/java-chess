package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.A3;
import static chess.fixture.PositionFixtures.H1;
import static chess.fixture.PositionFixtures.H8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("룩은 북쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookCanMove_When_DestinationIsAtNorth() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.canNotMoveByItsOwnInPassing(A1, A3)).isFalse();
    }

    @DisplayName("룩은 남쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookCanMove_When_DestinationIsAtSouth() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.canNotMoveByItsOwnInPassing(A3, A1)).isFalse();
    }

    @DisplayName("룩은 동쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookCanMove_When_DestinationIsAtEast() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.canNotMoveByItsOwnInPassing(A1, H1)).isFalse();
    }

    @DisplayName("룩은 서쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookCanMove_When_DestinationIsAtWest() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.canNotMoveByItsOwnInPassing(H1, A1)).isFalse();
    }

    @DisplayName("룩은 수직이 아닌 방향으로 이동할 수 없다")
    @Test
    void should_RookCanNotMove_When_DestinationIsDiagonal() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.canNotMoveByItsOwnInPassing(A1, H8)).isTrue();
    }

    @DisplayName("룩은 중간에 기물이 있는 경우 이동할 수 없다")
    @Test
    void should_RookCanNotMove_When_ObstacleIsOnPath() {
        Rook rook = new Rook(Team.WHITE);
        Pawn obstacle = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, rook);
        board.put(A2, obstacle);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(rook.canMove(A1, A3, chessBoard)).isFalse();
    }

    @DisplayName("룩은 시작 위치와 같은 위치로 이동할 수 없다")
    @Test
    void should_RookCanNotMove_When_DestinationIsSameWithStart() {
        Rook rook = new Rook(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, rook);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(rook.canMove(A1, A1, chessBoard)).isFalse();
    }

    @DisplayName("룩은 도착 위치에 같은 팀 기물이 있으면 이동할 수 없다")
    @Test
    void should_RookCanNotMove_When_FriendlyPieceAtDestination() {
        Rook rook = new Rook(Team.WHITE);
        Pawn friendlyPiece = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, rook);
        board.put(H1, friendlyPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(rook.canMove(A1, H1, chessBoard)).isFalse();
    }
}
