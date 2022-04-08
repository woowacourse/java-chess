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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KingTest {

    @Test
    @DisplayName("킹의 진행 방향과 거리가 맞는다면 true 반환")
    void correctDirectionMove() {
        King king = new King(Position.of('a', '1'), Team.WHITE);

        assertAll(
                () -> assertThat(king.isMovableRange(Position.of('a', '2'))).isTrue(),
                () -> assertThat(king.isMovableRange(Position.of('a', '2'))).isTrue(),
                () -> assertThat(king.isMovableRange(Position.of('b', '1'))).isTrue()
        );
    }

    @Test
    @DisplayName("킹이 이동할수 없는 거리이면 false 반환")
    void noCorrectDistanceMove() {
        King king = new King(Position.of('a', '1'), Team.WHITE);

        assertThat(king.isMovableRange(Position.of('c', '3'))).isFalse();
    }


    @Test
    @DisplayName("킹의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKingTest() {
        List<Piece> pieces = List.of(
                new King(Position.of('a', '8'), Team.BLACK),
                new Pawn(Position.of('a', '7'), Team.WHITE)
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "a7");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("킹의 target위치가 빈칸이면 움직임에 성공한다")
    void moveKingTest2() {
        List<Piece> pieces = List.of(
                new King(Position.of('a', '8'), Team.BLACK),
                new Empty(Position.of('b', '7'))
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "b7");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("킹의 target위치에 아군 말이 있으면 예외처리")
    void moveFailureKingTest() {
        List<Piece> pieces = List.of(
                new King(Position.of('a', '8'), Team.WHITE),
                new Pawn(Position.of('a', '7'), Team.WHITE)
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "a7");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.WHITE))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("킹은 source와 target사이에 말들이 없다.")
    void getIntervalPositionTest() {
        Piece rook = new Rook(Position.of('h', '8'), Team.BLACK);
        Piece king = new King(Position.of('e', '8'), Team.BLACK);
        List<Position> intervalPosition = king.getIntervalPosition(rook);

        assertThat(intervalPosition.isEmpty()).isTrue();
    }
}
