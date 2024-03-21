package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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

        Piece findPiece = board.findPieceByPosition(new Position(File.A, Rank.ONE));

        assertThat(findPiece).isEqualTo(new Rook(Color.WHITE));
    }

    @DisplayName("해당 위치에 기물이 없으면 Empty를 반환한다")
    @Test
    void findPieceByPosition_Empty() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(new Position(File.A, Rank.THREE));

        assertThat(findPiece).isEqualTo(new Empty());
    }

    @DisplayName("기물을 이동하는 경우 ")
    @Nested
    class whenMovePiece {
        @DisplayName("같은 위치면 예외를 발생한다")
        @Test
        void samePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position position = new Position(File.A, Rank.ONE);

            assertThatThrownBy(
                    () -> board.move(position, position, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("기물이 없으면 예외를 발생한다")
        @Test
        void emptyPiece() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = new Position(File.A, Rank.THREE);
            Position targetPosition = new Position(File.A, Rank.FOUR);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("내 말이 아니면 예외를 발생한다")
        @Test
        void notMyTurn() {
            Color turn = Color.BLACK;
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = new Position(File.A, Rank.ONE);
            Position targetPosition = new Position(File.A, Rank.TWO);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, turn))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("이동할 수 없는 위치면 예외를 발생한다")
        @Test
        void notMovablePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = new Position(File.A, Rank.ONE);
            Position targetPosition = new Position(File.A, Rank.FOUR);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("상대 진영 기물 위치로 이동했을 경우 제거하고 이동한다")
        @Test
        void whenMoveToEnemyPiece() {
            Position knightSourcePosition = new Position(File.D, Rank.THREE);
            Position knightTargetPosition = new Position(File.E, Rank.FIVE);

            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(knightSourcePosition, new Knight(Color.WHITE));
            pieces.put(knightTargetPosition, new Rook(Color.BLACK));

            Board board = new Board(pieces);

            board.move(knightSourcePosition, knightTargetPosition, Color.WHITE);

            assertThat(pieces).containsEntry(knightTargetPosition, new Knight(Color.WHITE));
        }
    }
}
