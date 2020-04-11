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

public class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"b4", "b6", "f4", "f6", "c3", "e3", "c7", "e7"})
    void canMove_이동_가능한_위치에_대하여_판단_시도(String to) {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new Knight(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of(to));

        assertThat(new Knight(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_이동_불가능한_위치에_대하여_판단_시도() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new Knight(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of("c4"));

        assertThat(new Knight(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void 인자있는_score_예외처리() {
        assertThatThrownBy(() -> {
            new Knight(Team.WHITE).getScore(true);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 인자없는_score() {
        assertThat(new Knight(Team.WHITE).getScore()).isEqualTo(2.5);
    }
}
