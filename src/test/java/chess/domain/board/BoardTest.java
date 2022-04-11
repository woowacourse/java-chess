package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.piece.King;
import chess.domain.piece.Queen;

class BoardTest {

    @Test
    @DisplayName("보드 생성기로 보드를 생성한다.")
    void createBoardByGenerator() {
        Board board = Board.of(new InitialBoardGenerator());
        assertThat(board).isNotNull();
    }

    @Test
    @DisplayName("말을 움직인다.")
    void move() {
        Route route = Route.of(List.of("a2", "a3"));
        Color turnColor = Color.WHITE;
        Board board = BoardFixtures.INITIAL;

        assertThatCode(() -> board.move(route, turnColor))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("적군을 움직일 수 없다.")
    void throwsExceptionWithEnemyMove() {
        Route route = Route.of(List.of("a2", "a3"));
        Color turnColor = Color.BLACK;
        Board board = BoardFixtures.INITIAL;

        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> board.move(route, turnColor));
    }

    @Test
    @DisplayName("아군을 공격할 수 없다.")
    void throwsExceptionWithAllyAttack() {
        Route route = Route.of(List.of("a1", "a2"));
        Color turnColor = Color.WHITE;
        Board board = BoardFixtures.INITIAL;

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> board.move(route, turnColor));
    }

    @Test
    @DisplayName("왕이 살아있는지를 알 수 있다.")
    void returnTrueWithKingDead() {
        Board board = BoardFixtures.create(Map.of(
            Point.of("e8"), new King(Color.BLACK),
            Point.of("e1"), new Queen(Color.WHITE)
        ));

        boolean isKingDead = board.isKingDead();

        assertThat(isKingDead).isTrue();
    }

    @Test
    @DisplayName("왕이 살아있으면 false를 반환한다.")
    void returnFalseWithKingAlive() {
        Board board = BoardFixtures.INITIAL;
        ;

        boolean isKingDead = board.isKingDead();

        assertThat(isKingDead).isFalse();
    }

    @Test
    @DisplayName("팀별 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        double initialTotalScore = 38;

        Board board = Board.of(new InitialBoardGenerator());

        assertThat(board.calculateScore().values())
            .containsExactly(initialTotalScore, initialTotalScore);
    }
}
