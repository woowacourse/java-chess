package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("해당 위치에 존재하는 기물을 반환한다")
    @Test
    void findPieceByPosition() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(PositionFixture.A1);

        assertThat(findPiece).isEqualTo(Rook.WHITE);
    }

    @DisplayName("해당 위치에 기물이 없으면 Empty를 반환한다")
    @Test
    void findPieceByPosition_Empty() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(PositionFixture.A3);

        assertThat(findPiece).isEqualTo(new Empty());
    }

    @DisplayName("기물을 이동하는 경우 ")
    @Nested
    class whenMovePiece {
        @DisplayName("같은 위치면 예외를 발생한다")
        @Test
        void samePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position position = PositionFixture.A1;

            assertThatThrownBy(
                    () -> board.move(position, position, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("기물이 없으면 예외를 발생한다")
        @Test
        void emptyPiece() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = PositionFixture.A3;
            Position targetPosition = PositionFixture.A4;

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("내 말이 아니면 예외를 발생한다")
        @Test
        void notMyTurn() {
            Color turn = Color.BLACK;
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = PositionFixture.A1;
            Position targetPosition = PositionFixture.A2;

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, turn))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("이동할 수 없는 위치면 예외를 발생한다")
        @Test
        void notMovablePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = PositionFixture.A1;
            Position targetPosition = PositionFixture.A4;

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("상대 진영 기물 위치로 이동했을 경우 제거하고 이동한다")
        @Test
        void whenMoveToEnemyPiece() {
            Position knightSourcePosition = PositionFixture.D3;
            Position knightTargetPosition = PositionFixture.E5;

            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(knightSourcePosition, Knight.WHITE);
            pieces.put(knightTargetPosition, Rook.BLACK);

            Board board = new Board(pieces);

            board.move(knightSourcePosition, knightTargetPosition, Color.WHITE);

            assertThat(pieces).containsEntry(knightTargetPosition, Knight.WHITE);
        }
    }
}
