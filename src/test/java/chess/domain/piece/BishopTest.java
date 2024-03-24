package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A8;
import static chess.fixture.PositionFixtures.B2;
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

class BishopTest {
    @DisplayName("비숍은 북동쪽에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_BishopCanMove_When_DestinationDirectionIsNE() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertThat(bishop.canNotMoveByItsOwnInPassing(A1, H8)).isFalse();
    }

    @DisplayName("비숍은 북서쪽에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_BishopCanMove_When_DestinationDirectionIsNW() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertThat(bishop.canNotMoveByItsOwnInPassing(H1, A8)).isFalse();
    }

    @DisplayName("비숍은 남동쪽에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_BishopCanMove_When_DestinationDirectionIsSE() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertThat(bishop.canNotMoveByItsOwnInPassing(A8, H1)).isFalse();
    }

    @DisplayName("비숍은 남서쪽에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_BishopCanMove_When_DestinationDirectionIsSW() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertThat(bishop.canNotMoveByItsOwnInPassing(H1, A8)).isFalse();
    }

    @DisplayName("비숍은 대각선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonalFromStartPosition() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertThat(bishop.canNotMoveByItsOwnInPassing(A1, H2)).isTrue();
    }

    @DisplayName("비숍은 중간에 기물이 있는 경우 이동할 수 없다")
    @Test
    void should_BishopCanNotMove_When_ObstacleIsOnPath() {
        Bishop bishop = new Bishop(Team.WHITE);
        Pawn obstacle = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, bishop);
        board.put(B2, obstacle);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(bishop.canMove(A1, H2, chessBoard)).isFalse();
    }

    @DisplayName("비숍은 시작 위치와 같은 위치로 이동할 수 없다")
    @Test
    void should_BishopCanNotMove_When_DestinationIsSameWithStart() {
        Bishop bishop = new Bishop(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, bishop);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(bishop.canMove(A1, A1, chessBoard)).isFalse();
    }

    @DisplayName("비숍은 도착 위치에 같은 팀 기물이 있으면 이동할 수 없다")
    @Test
    void should_BishopCanNotMove_When_FriendlyPieceAtDestination() {
        Bishop bishop = new Bishop(Team.WHITE);
        Pawn friendlyPiece = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, bishop);
        board.put(H8, friendlyPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(bishop.canMove(A1, H8, chessBoard)).isFalse();
    }
}
