package chess.model.piece;

import static chess.model.Team.BLACK;
import static chess.model.position.File.E;
import static chess.model.position.File.F;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.FOUR;
import static chess.model.position.Rank.SEVEN;
import static chess.model.position.Rank.SIX;
import static chess.model.position.Rank.THREE;
import static chess.model.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.Board;
import chess.model.position.File;
import chess.model.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PawnTest {

    @DisplayName("한 칸 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @EnumSource(File.class)
    void canMove_one_true(File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = board.get(Position.of(SEVEN, file));
        boolean actual = pawn.canMove(Position.of(SEVEN, file), Position.of(SIX, file), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("한 칸 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_one_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(BLACK);
        boolean actual = pawn.canMove(Position.of(THREE, E), Position.of(TWO, E), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("두 칸 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @EnumSource(File.class)
    void canMove_two_true(File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = board.get(Position.of(SEVEN, file));
        boolean actual = pawn.canMove(Position.of(SEVEN, file), Position.of(FIVE, file), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("두 칸 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_two_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(BLACK);
        boolean actual = pawn.canMove(Position.of(SIX, E), Position.of(FOUR, E), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("대각선 앞쪽으로 움직일 수 있으면 true를 반환한다.")
    @Test
    void canMove_side_true() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(BLACK);
        boolean actual = pawn.canMove(Position.of(THREE, E), Position.of(TWO, F), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("대각선 앞쪽으로 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_side_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(BLACK);
        boolean actual = pawn.canMove(Position.of(FIVE, E), Position.of(FOUR, F), board);

        assertThat(actual).isFalse();
    }
}
