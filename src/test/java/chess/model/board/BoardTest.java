package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    @Test
    void 보드는_기물이_없는_위치에_Empty를_둔다() {
        // given
        Board board = new Board(Map.of());

        // when, then
        for (int file = Board.MIN_LENGTH; file < Board.MAX_LENGTH; file++) {
            for (int rank = Board.MIN_LENGTH; rank < Board.MAX_LENGTH; rank++) {
                assertThat(board.getPiece(file, rank).isEmpty()).isTrue();
            }
        }
    }

    @Test
    void 이동_경로에_다른_기물이_있으면_예외가_발생한다() {
        // given
        Map<Position, Piece> piecePosition = Map.of(
                Position.of(1, 1), Queen.from(Color.WHITE),
                Position.of(1, 2), Pawn.from(Color.WHITE)
        );
        Board board = new Board(piecePosition);
        Movement movement = new Movement(Position.of(1, 1), Position.of(1, 3));

        // when, then
        assertThatThrownBy(() -> board.move(movement))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기물이_특정_위치로_움직일_수_있다면_움직인다() {
        // given
        Piece pawn = Pawn.from(Color.WHITE);
        Map<Position, Piece> piecePosition = Map.of(Position.of(2, 2), pawn);
        Board board = new Board(piecePosition);
        Movement movement = new Movement(Position.of(2, 2), Position.of(2, 3));

        // when
        board.move(movement);

        // then
        assertThat(board.getPiece(2, 3)).isEqualTo(pawn);
        assertThat(board.getPiece(2, 2).isEmpty()).isTrue();
    }
}
