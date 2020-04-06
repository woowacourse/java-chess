package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    void canMove_세로로_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Rook(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("c8"));

        assertThat(new Rook(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_세로로_후진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("h5"), new Rook(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("h5"), Position.of("h1"));

        assertThat(new Rook(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가로로_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Rook(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("h2"));

        assertThat(new Rook(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가로로_후진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Rook(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("a2"));

        assertThat(new Rook(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가는길에_장애물이_있을_때() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Rook(Team.WHITE));
        board.put(Position.of("c6"), new Rook(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("c8"));

        assertThat(new Rook(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void canMove_엉뚱한_방향으로_이동_요청시() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Rook(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("a3"));

        assertThat(new Rook(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void 인자있는_score_예외처리() {
        assertThatThrownBy(() -> {
            new Rook(Team.WHITE).getScore(true);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 인자없는_score() {
        assertThat(new Rook(Team.WHITE).getScore()).isEqualTo(5.0);
    }
}
