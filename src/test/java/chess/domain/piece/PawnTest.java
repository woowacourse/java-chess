package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @Test
    void canMove_처음_움직일_때_2칸_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Pawn(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("c4"));

        assertThat(new Pawn(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_1칸_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c3"), new Pawn(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c3"), Position.of("c4"));

        assertThat(new Pawn(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_대각선상에_적이있을_때_대각선_한칸_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Pawn(Team.WHITE));
        board.put(Position.of("b3"), new Pawn(Team.BLACK));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("b3"));

        assertThat(new Pawn(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_처음이_아닐_때_2칸_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c3"), new Pawn(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c3"), Position.of("c5"));

        assertThat(new Pawn(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void canMove_역방향_한칸_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c3"), new Pawn(Team.BLACK));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c3"), Position.of("c4"));

        assertThat(new Pawn(Team.BLACK).canMove(moveInformation)).isFalse();
    }

    @Test
    void canMove_대각선상에_적이없을_때_대각선_한칸_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Pawn(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("b3"));

        assertThat(new Pawn(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void 인자없는_getScore_예외처리() {
        assertThatThrownBy(() -> {
            new Pawn(Team.WHITE).getScore();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"true:0.5", "false:1.0"}, delimiter = ':')
    void 인자있는_getScore(boolean mustPawnScoreDowngraded, double expected) {
        assertThat(new Pawn(Team.WHITE).getScore(mustPawnScoreDowngraded)).isEqualTo(expected);
    }
}
