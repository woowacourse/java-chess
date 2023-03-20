package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.piece.AllPiecesGenerator;
import chess.piece.Pawn;
import chess.piece.Pieces;
import chess.piece.Rook;
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
        board = new Board(new Pieces(new AllPiecesGenerator()));
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
        )));
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
        )));
        final int beforeMoveSize = fixedBoard.getPieces().size();

        // when
        fixedBoard.movePiece(sourcePosition, targetPosition);
        final int afterMoveSize = fixedBoard.getPieces().size();

        // then
        assertThat(afterMoveSize).isEqualTo(beforeMoveSize);
    }
}
