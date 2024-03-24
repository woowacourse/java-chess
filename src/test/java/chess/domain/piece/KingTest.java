package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static chess.fixture.PositionFixtures.C1;
import static chess.fixture.PositionFixtures.C2;
import static chess.fixture.PositionFixtures.D3;
import static chess.fixture.PositionFixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("킹은 북쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsNorth() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(A1, A2)).isFalse();
    }

    @DisplayName("킹은 남쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsSouth() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(A2, A1)).isFalse();
    }

    @DisplayName("킹은 서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsWest() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(B1, A1)).isFalse();
    }

    @DisplayName("킹은 동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsEast() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(A1, B1)).isFalse();
    }


    @DisplayName("킹은 북동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsNorthEast() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(B1, C2)).isFalse();
    }

    @DisplayName("킹은 북서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsNorthWest() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(B1, A2)).isFalse();
    }

    @DisplayName("킹은 남동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsSouthEast() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(B2, C1)).isFalse();
    }

    @DisplayName("킹은 남서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMove_When_DestinationDirectionIsSouthWest() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(B2, A1)).isFalse();
    }

    @DisplayName("킹은 한칸 이상 움직일 수 없다")
    @Test
    void should_KingCanNotMove_When_DestinationIsToFar() {
        King king = new King(Team.BLACK);
        assertThat(king.canNotMoveByItsOwnInPassing(A1, D3)).isTrue();
    }

    @DisplayName("킹은 시작 위치와 같은 위치로 이동할 수 없다")
    @Test
    void should_BishopCanNotMove_When_DestinationIsSameWithStart() {
        King king = new King(Team.BLACK);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, king);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(king.canMove(A1, A1, chessBoard)).isFalse();
    }

    @DisplayName("킹은 도착 위치에 같은 팀 기물이 있으면 이동할 수 없다")
    @Test
    void should_BishopCanNotMove_When_FriendlyPieceAtDestination() {
        King king = new King(Team.WHITE);
        Pawn friendlyPiece = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, king);
        board.put(H8, friendlyPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(king.canMove(A1, H8, chessBoard)).isFalse();
    }
}
