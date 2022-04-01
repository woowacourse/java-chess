package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BoardTest {

    @Test
    @DisplayName("보드 생성기로 보드를 생성한다.")
    void createBoardByGenerator() {
        Board board = new Board(InitialBoardGenerator.generate());
        assertThat(board).isNotNull();
    }

    @Test
    @DisplayName("말을 움직인다.")
    void move() {
        Point from = Point.of("a2");
        Point to = Point.of("a3");
        Color turnColor = Color.WHITE;
        Board board = BoardFixtures.initial();

        assertThatCode(() -> board.move(from, to, turnColor))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("적군을 움직일 수 없다.")
    void throwsExceptionWithEnemyMove() {
        Point from = Point.of("a2");
        Point to = Point.of("a3");
        Color turnColor = Color.BLACK;
        Board board = BoardFixtures.initial();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.move(from, to, turnColor));
    }

    @Test
    @DisplayName("아군을 공격할 수 없다.")
    void throwsExceptionWithAllyAttack() {
        Point from = Point.of("a1");
        Point to = Point.of("a2");
        Color turnColor = Color.WHITE;
        Board board = BoardFixtures.initial();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.move(from, to, turnColor));
    }

    @Test
    @DisplayName("왕이 죽으면 true를 반환한다.")
    void returnTrueWithKingDead() {
        Point from = Point.of("e1");
        Point to = Point.of("e8");
        Board board = BoardFixtures.create(Map.of(
                Point.of("e8"), new King(Color.BLACK),
                Point.of("e1"), new Queen(Color.WHITE)
        ));

        board.move(from, to, Color.WHITE);
        assertThat(board.isKingDead(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("왕이 살아있으면 false를 반환한다.")
    void returnFalseWithKingAlive() {
        Point from = Point.of("a2");
        Point to = Point.of("a3");
        Board board = BoardFixtures.initial();

        board.move(from, to, Color.WHITE);
        assertThat(board.isKingDead(Color.BLACK)).isFalse();
    }

    @Test
    @DisplayName("팀별 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        double initialTotalScore = 38;

        Board board = new Board(InitialBoardGenerator.generate());

        assertThat(board.calculateScore().values())
                .containsExactly(initialTotalScore, initialTotalScore);
    }
}
