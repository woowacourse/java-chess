package chess.board.piece;

import chess.board.Board;
import chess.board.Team;
import chess.board.Turn;
import chess.board.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {

    @Test
    @DisplayName("나이트의 진행 방향이 맞는다면 true 반환")
    void isCorrectMovable() {
        Knight knight = new Knight(Position.of('a', '1'), Team.WHITE);

        assertThat(knight.isMovableRange(Position.of('b', '3'))).isTrue();
    }

    @Test
    @DisplayName("나이트의 진행 방향이 틀리다면 false 반환")
    void isNotCorrectMovable() {
        Knight knight = new Knight(Position.of('a', '1'), Team.WHITE);

        assertThat(knight.isMovableRange(Position.of('b', '2'))).isFalse();
    }

    @Test
    @DisplayName("나이트의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKnightTest() {
        List<Piece> pieces = List.of(
                new Knight(Position.of('a', '8'), Team.BLACK),
                new Pawn(Position.of('b', '6'), Team.WHITE)
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "b6");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("나이트가 target위치로 진행할때 방해물이 있으면 넘어서 진행한다.")
    void moveKnightTest2() {

        Board board = Board.create(Pieces.createInit(), Turn.init());
        List<String> command = List.of("g1", "h3");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.WHITE))
        );
    }

    @Test
    @DisplayName("나이트의 target위치에 아군 말이 있으면 예외처리")
    void moveFailureKnightTest() {
        List<Piece> pieces = List.of(
                new Knight(Position.of('a', '8'), Team.WHITE),
                new Pawn(Position.of('c', '7'), Team.WHITE)
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "c7");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.WHITE))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
