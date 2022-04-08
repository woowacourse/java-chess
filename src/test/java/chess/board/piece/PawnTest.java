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

class PawnTest {

    @Test
    @DisplayName("폰은 처음 상태에서 1칸 또는 2칸 전진할 수 있으면 true 반환")
    void isCorrectMovable() {
        Pawn pawn = new Pawn(Position.of('a', '2'), Team.WHITE);

        assertAll(
                () -> assertThat(pawn.isMovableRange(Position.of('a', '3'))).isTrue(),
                () -> assertThat(pawn.isMovableRange(Position.of('a', '4'))).isTrue()
        );
    }

    @Test
    @DisplayName("폰은 처음 상태에서 3칸 전진하려면 false 반환")
    void isNotCorrectInitMovable() {
        Pawn pawn = new Pawn(Position.of('a', '2'), Team.WHITE);

        assertThat(pawn.isMovableRange(Position.of('a', '5'))).isFalse();
    }

    @Test
    @DisplayName("폰은 한번 이동한 후, 2칸 움직이면 false 반환")
    void isNotCorrectMovable() {
        Pawn pawn = new Pawn(Position.of('a', '3'), Team.WHITE);

        assertThat(pawn.isMovableRange(Position.of('a', '5'))).isFalse();
    }

    @Test
    @DisplayName("폰이 뒤로 움직이면 false 반환")
    void isNotCorrectBackMovable() {
        Pawn pawn = new Pawn(Position.of('a', '3'), Team.WHITE);

        assertThat(pawn.isMovableRange(Position.of('a', '2'))).isFalse();
    }

    @Test
    @DisplayName("폰이 블랙팀이면 Rank가 감소하는 방향으로 알맞게 움직이면 true 반환")
    void isCorrectMovableWhenTeamIsBlack() {
        Pawn pawn = new Pawn(Position.of('a', '7'), Team.BLACK);

        assertThat(pawn.isMovableRange(Position.of('a', '6'))).isTrue();
    }

    @Test
    @DisplayName("블랙 폰의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKingTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '8'), Team.BLACK),
                new Empty(Position.of('a', '7'))
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "a7");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("화이트 폰의 target위치가 빈칸이면 움직임에 성공한다")
    void moveKingTest2() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '2'), Team.WHITE),
                new Empty(Position.of('a', '3'))
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a2", "a3");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.WHITE))
        );
    }

    @Test
    @DisplayName("폰의 target위치에 말이 있으면 예외처리")
    void moveFailureKingTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '8'), Team.BLACK),
                new King(Position.of('a', '7'), Team.WHITE)
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "a7");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 source와 target사이에 말들이 없다.")
    void getIntervalPositionTest() {
        Piece pawn = new Pawn(Position.of('a', '8'), Team.BLACK);
        Piece king = new King(Position.of('a', '6'), Team.BLACK);
        List<Position> intervalPosition = pawn.getIntervalPosition(king);

        assertThat(intervalPosition.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("폰은 처음 위치가 아닌곳에서 움직일때는 한 칸만 움직일수 있다.")
    void movePawnOnlyOneStepTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '6'), Team.BLACK),
                new Empty(Position.of('a', '5')),
                new Empty(Position.of('a', '4'))
        );

        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a6", "a4");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 뒤로 움직이면 예외가 발생한다.")
    void movePawnBackOneStepTest() {
        List<Piece> pieces = List.of(
                new Empty(Position.of('a', '7')),
                new Pawn(Position.of('a', '6'), Team.BLACK)
        );

        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a6", "a7");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("블랙팀 폰이 대각선의 상대 말을 잡아먹는다.")
    void killBlackPawnTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '8'), Team.BLACK),
                new Knight(Position.of('b', '7'), Team.WHITE)
        );

        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "b7");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("하얀팀 폰이 대각선의 상대 말을 잡아먹는다.")
    void killWhitePawnTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '2'), Team.WHITE),
                new Knight(Position.of('b', '3'), Team.BLACK)
        );

        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a2", "b3");

        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.WHITE))
        );
    }

    @Test
    @DisplayName("폰이 대각선에 아군 말이 있으면 잡아먹지않는다.")
    void killFailureWhenOurTeamTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('a', '8'), Team.BLACK),
                new Knight(Position.of('b', '7'), Team.BLACK)
        );

        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "b7");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 대각선 뒤의 상대 말은 잡아먹을수 없다.")
    void killFailureWhenBackStepTest() {
        List<Piece> pieces = List.of(
                new Pawn(Position.of('b', '7'), Team.BLACK),
                new Knight(Position.of('a', '8'), Team.WHITE)
        );

        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("b7", "a8");

        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
