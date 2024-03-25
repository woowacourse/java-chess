package chess.domain.piece;

import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.A3;
import static chess.fixture.PositionFixtures.A4;
import static chess.fixture.PositionFixtures.B3;
import static chess.fixture.PositionFixtures.C3;
import static chess.fixture.PositionFixtures.C4;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("폰은 시작 위치에서 한 칸 앞으로 이동할 수 있다")
    @Test
    void should_PawnCanMoveForwardOneSquare_When_StartingPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A2, pawn);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(A2, A3, chessBoard)).isTrue();
    }

    @DisplayName("폰은 시작 위치에서 두 칸 앞으로 이동할 수 있다")
    @Test
    void should_PawnCanMoveForwardTwoSquares_When_StartingPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A2, pawn);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(A2, A4, chessBoard)).isTrue();
    }

    @DisplayName("폰은 다른 기물이 가로막을 경우 시작 위치에서 두 칸 앞으로 이동할 수 없다")
    @Test
    void should_PawnCanNotMoveForwardTwoSquares_When_ObstacleIsOnPath() {
        Pawn pawn = new Pawn(Team.WHITE);
        Pawn obstacle = new Pawn(Team.BLACK);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A2, pawn);
        board.put(A3, obstacle);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(A2, A4, chessBoard)).isFalse();
    }

    @DisplayName("폰은 시작 위치가 아닌 경우 한 칸 앞으로 이동할 수 있다")
    @Test
    void should_PawnCanMoveForwardOneSquare_When_NotStartingPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(C3, pawn);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(C3, C4, chessBoard)).isTrue();
    }

    @DisplayName("폰은 대각선으로 이동할 수 있다 (상대 기물을 잡을 때)")
    @Test
    void should_PawnCanMoveDiagonally_When_CapturingOpponentPiece() {
        Pawn pawn = new Pawn(Team.WHITE);
        Pawn opponentPiece = new Pawn(Team.BLACK);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A2, pawn);
        board.put(B3, opponentPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(A2, B3, chessBoard)).isTrue();
    }

    @DisplayName("폰은 대각선으로 이동할 수 없다 (상대 기물이 없을 때)")
    @Test
    void should_PawnCanNotMoveDiagonally_When_NoOpponentPieceToCapture() {
        Pawn pawn = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A2, pawn);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(A2, B3, chessBoard)).isFalse();
    }

    @DisplayName("폰은 대각선으로 이동할 수 없다 (같은 팀 기물이 있을 때)")
    @Test
    void should_PawnCanNotMoveDiagonally_When_FriendlyPieceAtDestination() {
        Pawn pawn = new Pawn(Team.WHITE);
        Pawn friendlyPiece = new Pawn(Team.WHITE);
        Map<Position, Piece> board = new HashMap<>();
        board.put(A2, pawn);
        board.put(B3, friendlyPiece);
        ChessBoard chessBoard = new ChessBoard(board);

        assertThat(pawn.canMove(A2, B3, chessBoard)).isFalse();
    }
}
