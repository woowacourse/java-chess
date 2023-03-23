package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.piece.AllPiecesGenerator;
import chess.piece.Pawn;
import chess.piece.Pieces;
import chess.piece.sliding.Rook;
import chess.piece.Side;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board(new Pieces(new AllPiecesGenerator()), Side.WHITE);
    }

    @Test
    @DisplayName("기물이 해당 위치로 갈 수 없으면 예외를 던진다.")
    void movePiece_throws() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.B, Rank.FIVE);

        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("타겟 위치까지의 경로에 말이 존재하는 경우 예외를 던진다.")
    void movePiece_pieceExistOnPath_throws() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.A, Rank.FOUR);

        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치까지의 경로에 말이 존재합니다.");
    }

    @Test
    @DisplayName("타겟 위치에 아군 말이 존재하는 경우 예외를 던진다.")
    void movePiece_sameSidePieceExistOnTarget_throws() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.A, Rank.TWO);

        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 아군 말이 존재합니다.");
    }

    @Test
    @DisplayName("나이트는 이동 경로에 말이 있어도 타겟 지점으로 이동할 수 있다.")
    void movePiece_pieceOnKnightPath_success() {
        // given
        final Position knightPosition = new Position(File.B, Rank.ONE);
        final Position targetPosition = new Position(File.C, Rank.THREE);

        // when, then
        assertDoesNotThrow(() -> board.movePiece(knightPosition, targetPosition));
    }

    @Test
    @DisplayName("흰색 기물이 이동한 곳에 검은색 기물이 있다면 검은색 기물을 게임에서 제거한다.")
    void checkEnemyPieceOnTargetPosition_exist() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.A, Rank.SEVEN);
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new Rook(sourcePosition, Side.WHITE),
                new Pawn(targetPosition, Side.BLACK)
        )), Side.WHITE);
        final int beforeMoveSize = fixedBoard.getPieces().size();

        // when
        fixedBoard.movePiece(sourcePosition, targetPosition);
        final int afterMoveSize = fixedBoard.getPieces().size();

        // then
        assertThat(afterMoveSize).isEqualTo(beforeMoveSize - 1);
    }

    @Test
    @DisplayName("흰색 기물이 이동한 곳에 검은색 기물이 존재하지 않는다면 어떠한 기물도 제거되지 않는다.")
    void checkEnemyPieceOnTargetPosition_nonExist() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.A, Rank.SIX);
        final Position pawnPosition = new Position(File.A, Rank.SEVEN);
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new Rook(sourcePosition, Side.WHITE),
                new Pawn(pawnPosition, Side.BLACK)
        )), Side.WHITE);
        final int beforeMoveSize = fixedBoard.getPieces().size();

        // when
        fixedBoard.movePiece(sourcePosition, targetPosition);
        final int afterMoveSize = fixedBoard.getPieces().size();

        // then
        assertThat(afterMoveSize).isEqualTo(beforeMoveSize);
    }

    @Test
    @DisplayName("적 기물이 대각 방향에 있을 때, 폰은 해당 방향으로 이동하며 적 기물을 잡을 수 있다.")
    void checkPieceMovable_pawnDiagonalMove_success() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.FOUR);
        final Position targetPosition = new Position(File.C, Rank.FIVE);
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new Pawn(sourcePosition, Side.WHITE),
                new Pawn(targetPosition, Side.BLACK)
        )), Side.WHITE);
        final int beforeMoveSize = fixedBoard.getPieces().size();

        // when
        fixedBoard.movePiece(sourcePosition, targetPosition);
        final int afterMoveSize = fixedBoard.getPieces().size();

        // then
        assertThat(afterMoveSize).isEqualTo(beforeMoveSize - 1);
    }

    @Test
    @DisplayName("적 기물이 대각 방향에 존재하지 않는 상태에서 폰을 대각 방향으로 이동하면 예외가 발생한다.")
    void checkPieceMovable_pawnDiagonalMove_throws() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.FOUR);
        final Position targetPosition = new Position(File.C, Rank.FIVE);
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new Pawn(sourcePosition, Side.WHITE)
        )), Side.WHITE);

        // when, then
        assertThatThrownBy(() -> fixedBoard.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 대각 방향에 적이 있을 때만 대각으로 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("폰이 전진하려 하는 위치에 적 기물이 존재하면 예외를 던진다.")
    void checkPieceMovable_pawnVerticalMove_throws() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.FOUR);
        final Position targetPosition = new Position(File.B, Rank.FIVE);
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new Pawn(sourcePosition, Side.WHITE),
                new Pawn(targetPosition, Side.BLACK)
        )), Side.WHITE);

        // when, then
        assertThatThrownBy(() -> fixedBoard.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 대각으로 이동할 때만 적 기물이 있는 위치로 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 진영이 기물을 옮길 차례일 때, 검은색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_whiteTurn_throws() {
        // given
        final Position blackPiecePosition = new Position(File.A, Rank.SEVEN);
        final Position targetPosition = new Position(File.A, Rank.SIX);

        // when, then
        assertThatThrownBy(() -> board.movePiece(blackPiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("검은색 진영이 기물을 옮길 차례일 때, 흰색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_blackTurn_throws() {
        // given
        final Board blackFirstBoard = new Board(new Pieces(new AllPiecesGenerator()), Side.BLACK);
        final Position whitePiecePosition = new Position(File.A, Rank.TWO);
        final Position targetPosition = new Position(File.A, Rank.THREE);

        // when, then
        assertThatThrownBy(() -> blackFirstBoard.movePiece(whitePiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }
}
