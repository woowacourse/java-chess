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

public class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"d4", "d6", "c5", "e5", "c4", "e4", "c6", "e6"})
    void canMove_인접위치로_이동(String to) {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new King(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of(to));

        assertThat(new King(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가려는_위치에_같은팀_말이_있을_때() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new King(Team.WHITE));
        board.put(Position.of("e4"), new Pawn(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of("e4"));

        assertThat(new King(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void 인자있는_score_예외처리() {
        assertThatThrownBy(() -> {
            new King(Team.WHITE).getScore(true);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 인자없는_score() {
        assertThat(new King(Team.WHITE).getScore()).isEqualTo(0.0);
    }
}
