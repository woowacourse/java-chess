package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.A8;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static chess.fixture.PositionFixtures.B3;
import static chess.fixture.PositionFixtures.B6;
import static chess.fixture.PositionFixtures.B8;
import static chess.fixture.PositionFixtures.C3;
import static chess.fixture.PositionFixtures.C6;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("나이트는 북북동으로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtNNE() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(A1, B3)).isFalse();
    }

    @DisplayName("나이트는 북북서로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtNNW() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(B3, A1)).isFalse();
    }

    @DisplayName("나이트는 남남동으로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtSSE() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(A8, B6)).isFalse();
    }

    @DisplayName("나이트는 남남서로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtSSW() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(B6, A8)).isFalse();
    }

    @DisplayName("나이트는 동동북으로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtEEN() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(B1, C3)).isFalse();
    }

    @DisplayName("나이트는 동동남으로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtEES() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(B8, C6)).isFalse();
    }

    @DisplayName("나이트는 서서북으로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtWWN() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(C3, B1)).isFalse();
    }

    @DisplayName("나이트는 서서남으로 움직일 수 있다")
    @Test
    void should_KnightCanMove_When_DestinationIsAtWWS() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(C6, B8)).isFalse();
    }

    @DisplayName("나이트는 행마법을 벗어난 목적지로 이동할 수 없다")
    @Test
    void should_KnightCanNotMove_When_DestinationIsNotInKnightPassing() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.canNotMoveByItsOwnInPassing(B1, B2)).isTrue();
    }

    @DisplayName("나이트는 이동 경로에 기물이 있더라도 이동할 수 있다")
    @Test
    void should_KnightCanMove_When_PassHasObstacle() {
        Knight knight = new Knight(Team.BLACK);
        Pawn obstacle = new Pawn(Team.BLACK);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, knight);
        board.put(A2, obstacle);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(knight.canMove(A1, B3, chessBoard)).isTrue();
    }

    @DisplayName("나이트는 시작 위치와 같은 위치로 이동할 수 없다")
    @Test
    void should_KnightCanNotMove_When_DestinationIsSameWithStart() {
        Knight knight = new Knight(Team.BLACK);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, knight);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(knight.canMove(A1, A1, chessBoard)).isFalse();
    }

    @DisplayName("나이트는 도착 위치에 같은 팀 기물이 있으면 이동할 수 없다")
    @Test
    void should_KnightCanNotMove_When_FriendlyPieceAtDestination() {
        Knight knight = new Knight(Team.BLACK);
        Pawn friendlyPiece = new Pawn(Team.BLACK);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, knight);
        board.put(B3, friendlyPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(knight.canMove(A1, B3, chessBoard)).isFalse();
    }
}
