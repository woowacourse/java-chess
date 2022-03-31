package chess.model.piece;

import chess.model.Board;
import chess.model.Position;
import chess.model.Team;
import chess.model.Turn;
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
        Position source = Position.from("a1");
        Position target = Position.from("f1");

        assertThat(rook.isMovable(source, target)).isTrue();
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.(가로)")
    void getIntervalPositionTest(){
        Piece rook = new Rook(Position.of('h','8'),Team.BLACK);
        List<Position> intervalPosition = rook.getIntervalPosition(Position.from("h8"), Position.from("e8"));

        assertThat(intervalPosition.contains(Position.of('f','8'))).isTrue();
        assertThat(intervalPosition.contains(Position.of('g','8'))).isTrue();
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.(세로)")
    void getIntervalPositionVerticalTest(){
        Piece rook = new Rook(Position.of('h','8'),Team.BLACK);
        List<Position> intervalPosition = rook.getIntervalPosition(Position.from("h8"), Position.from("h5"));

        assertThat(intervalPosition.contains(Position.from("h7"))).isTrue();
        assertThat(intervalPosition.contains(Position.from("h6"))).isTrue();
    }

    @Test
    @DisplayName("룩의 진행방향에 말이 있으면 예외 처리")
    void moveFailureWhenExistPieceTest() {
        Board board = Board.create(Pieces.create());
        String source = "a8";
        String target = "a5";

        assertThatThrownBy(
                () -> board.move(source, target, new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 있으면 예외 처리")
    void moveFailureTest() {
        Board board = Board.create(Pieces.create());
        String source = "a8";
        String target = "a7";

        assertThatThrownBy(
                () -> board.move(source, target, new Turn(Team.BLACK))
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
        Board board = Board.create(Pieces.of(pieces));
        String source = "a8";
        String target = "a5";


        assertDoesNotThrow(
                () -> board.move(source, target, new Turn(Team.BLACK))
        );
    }
}
