package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
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

    @DisplayName("초기 보드 생성 후 존재하는 기물들을 전부 확인한다")
    @Test
    void createInitialBoard() {
        Board board = BoardFactory.createInitialBoard();

        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.from(File.A, Rank.ONE), Rook.WHITE);
        pieces.put(Position.from(File.B, Rank.ONE), Knight.WHITE);
        pieces.put(Position.from(File.C, Rank.ONE), Bishop.WHITE);
        pieces.put(Position.from(File.D, Rank.ONE), Queen.WHITE);
        pieces.put(Position.from(File.E, Rank.ONE), King.WHITE);
        pieces.put(Position.from(File.F, Rank.ONE), Bishop.WHITE);
        pieces.put(Position.from(File.G, Rank.ONE), Knight.WHITE);
        pieces.put(Position.from(File.H, Rank.ONE), Rook.WHITE);
        pieces.put(Position.from(File.A, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.B, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.C, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.D, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.E, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.F, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.G, Rank.TWO), Pawn.ofWhite());
        pieces.put(Position.from(File.H, Rank.TWO), Pawn.ofWhite());

        pieces.put(Position.from(File.A, Rank.EIGHT), Rook.BLACK);
        pieces.put(Position.from(File.B, Rank.EIGHT), Knight.BLACK);
        pieces.put(Position.from(File.C, Rank.EIGHT), Bishop.BLACK);
        pieces.put(Position.from(File.D, Rank.EIGHT), Queen.BLACK);
        pieces.put(Position.from(File.E, Rank.EIGHT), King.BLACK);
        pieces.put(Position.from(File.F, Rank.EIGHT), Bishop.BLACK);
        pieces.put(Position.from(File.G, Rank.EIGHT), Knight.BLACK);
        pieces.put(Position.from(File.H, Rank.EIGHT), Rook.BLACK);
        pieces.put(Position.from(File.A, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.B, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.C, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.D, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.E, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.F, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.G, Rank.SEVEN), Pawn.ofBlack());
        pieces.put(Position.from(File.H, Rank.SEVEN), Pawn.ofBlack());

        assertThat(board.getPieces()).isEqualTo(pieces);
    }

    @DisplayName("해당 위치에 존재하는 기물을 반환한다")
    @Test
    void findPieceByPosition() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(Position.from(File.A, Rank.ONE));

        assertThat(findPiece).isEqualTo(Rook.WHITE);
    }

    @DisplayName("해당 위치에 기물이 없으면 Empty를 반환한다")
    @Test
    void findPieceByPosition_Empty() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(Position.from(File.A, Rank.THREE));

        assertThat(findPiece).isEqualTo(new Empty());
    }

    @DisplayName("기물을 이동하는 경우 ")
    @Nested
    class whenMovePiece {
        @DisplayName("같은 위치면 예외를 발생한다")
        @Test
        void samePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position position = Position.from(File.A, Rank.ONE);

            assertThatThrownBy(
                    () -> board.move(position, position, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("기물이 없으면 예외를 발생한다")
        @Test
        void emptyPiece() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = Position.from(File.A, Rank.THREE);
            Position targetPosition = Position.from(File.A, Rank.FOUR);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("내 말이 아니면 예외를 발생한다")
        @Test
        void notMyTurn() {
            Color turn = Color.BLACK;
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = Position.from(File.A, Rank.ONE);
            Position targetPosition = Position.from(File.A, Rank.TWO);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, turn))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("이동할 수 없는 위치면 예외를 발생한다")
        @Test
        void notMovablePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = Position.from(File.A, Rank.ONE);
            Position targetPosition = Position.from(File.A, Rank.FOUR);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("상대 진영 기물 위치로 이동했을 경우 제거하고 이동한다")
        @Test
        void whenMoveToEnemyPiece() {
            Position knightSourcePosition = Position.from(File.D, Rank.THREE);
            Position knightTargetPosition = Position.from(File.E, Rank.FIVE);

            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(knightSourcePosition, Knight.WHITE);
            pieces.put(knightTargetPosition, Rook.BLACK);

            Board board = new Board(pieces);

            board.move(knightSourcePosition, knightTargetPosition, Color.WHITE);

            assertThat(pieces).containsEntry(knightTargetPosition, Knight.WHITE);
        }
    }
}
