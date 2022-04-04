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

class RookTest {

    @Test
    @DisplayName("룩의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Rook rook = new Rook(Position.of('a', '1'), Team.WHITE);

        assertThat(rook.isMovableRange(Position.of('f', '1'))).isTrue();
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.")
    void getIntervalPositionTest() {
        Piece rook = new Rook(Position.of('h', '8'), Team.BLACK);
        Piece king = new King(Position.of('e', '8'), Team.BLACK);
        List<Position> intervalPosition = rook.getIntervalPosition(king);

        assertThat(intervalPosition.contains(Position.of('f', '8'))).isTrue();
        assertThat(intervalPosition.contains(Position.of('g', '8'))).isTrue();
    }

    @Test
    @DisplayName("룩의 진행방향에 말이 있으면 예외 처리")
    void moveFailureWhenExistPieceTest() {
        Board board = Board.create(Pieces.createInit(), Turn.init());
        List<String> command = List.of("a8", "a5");
        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 있으면 예외 처리")
    void moveFailureTest() {
        Board board = Board.create(Pieces.createInit(), Turn.init());
        List<String> command = List.of("a8", "a7");
        assertThatThrownBy(
                () -> board.move(command, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveTest() {
        List<Piece> pieces = List.of(
                new Rook(Position.of('a', '8'), Team.BLACK),
                new Empty(Position.of('a', '7')),
                new Empty(Position.of('a', '6')),
                new Pawn(Position.of('a', '5'), Team.WHITE)
        );
        Board board = Board.create(Pieces.from(pieces), Turn.init());
        List<String> command = List.of("a8", "a5");


        assertDoesNotThrow(
                () -> board.move(command, new Turn(Team.BLACK))
        );
    }
}
