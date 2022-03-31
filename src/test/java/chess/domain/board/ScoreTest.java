package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("생성 후 값 확인")
    @Test
    void test() {
        // given
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(Column.A, Row.EIGHT), new Rook(Team.BLACK)); // 5.0
        board.put(Position.of(Column.B, Row.EIGHT), new Knight(Team.BLACK)); // 2.5
        board.put(Position.of(Column.C, Row.EIGHT), new Bishop(Team.BLACK)); // 3.0

        Score score = new Score(board);
        Map<Team, Double> value = score.getValue();

        // then
        assertThat(value.get(Team.BLACK)).isEqualTo(10.5f);
    }

    @DisplayName("같은 열에 pawn 존재")
    @Test
    void test2() {
        // given
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(Column.A, Row.ONE), new Pawn(Team.BLACK)); // 0.5
        board.put(Position.of(Column.A, Row.TWO), new Pawn(Team.BLACK)); // 0.5
        board.put(Position.of(Column.A, Row.THREE), new Pawn(Team.BLACK)); // 0.5
        board.put(Position.of(Column.B, Row.THREE), new Pawn(Team.BLACK)); // 1.0
        board.put(Position.of(Column.A, Row.FOUR), new Pawn(Team.WHITE)); // 1.0

        Score score = new Score(board);
        Map<Team, Double> value = score.getValue();

        // then
        assertThat(value.get(Team.BLACK)).isEqualTo(2.5f);
    }

    @DisplayName("이긴 팀 찾기")
    @Test
    void win() {
        // given
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(Column.A, Row.ONE), new Pawn(Team.BLACK));

        Score score = new Score(board);

        // then
        assertThat(score.findWinTeam()).isEqualTo(Team.BLACK);
    }

    @DisplayName("무승부")
    @Test
    void draw() {
        // given
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(Column.A, Row.ONE), new Pawn(Team.BLACK));
        board.put(Position.of(Column.A, Row.TWO), new Pawn(Team.WHITE));

        Score score = new Score(board);

        // then
        assertThat(score.findWinTeam()).isNull();
    }
}