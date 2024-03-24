package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.B2;
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

    /*
    .......b
    ......*.
    .....*..
    ....*...
    ...*....
    ..*.....
    .*......
    b.......
    */
    @DisplayName("비숍은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        Bishop bishop = new Bishop(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, bishop);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(bishop.canMove(A1, H8, chessBoard)).isTrue();
    }

    /*
    ........
    ........
    ........
    ........
    ........
    ........
    .......b
    b.......
    */
    @DisplayName("비숍은 대각선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonal_From_StartPosition() {
        Bishop bishop = new Bishop(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A1, bishop);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(bishop.canMove(A1, H2, chessBoard)).isFalse();
    }

    /*
    .......b
    ........
    ........
    ........
    ........
    ........
    .x......
    b.......
    */
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
}
