package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.piece.Camp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    private static final int INITIAL_GAME_SCORE = 38;
    private static final int TWO_VERTICAL_PAWN_SCORE = 37;
    private Game game;

    @BeforeEach
    void setup() {
        game = new Game();
    }

    @DisplayName("White의 말을 한 번 움직이면 Black의 차례로 넘어간다.")
    @Test
    void Should_NextTurn_When_Move() {
        game.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.THREE));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> game.move(new Square(File.B, Rank.THREE), new Square(File.B, Rank.FOUR))
        );
    }

    @DisplayName("White의 초기 점수를 계산한다.")
    @Test
    void Should_CalculateScore_When_White() {
        assertThat(game.calculateScore(Camp.WHITE)).isEqualTo(INITIAL_GAME_SCORE);
    }

    @DisplayName("Black의 초기 점수를 계산한다.")
    @Test
    void Should_CalculateScore_When_Black() {
        assertThat(game.calculateScore(Camp.WHITE)).isEqualTo(INITIAL_GAME_SCORE);
    }

    @DisplayName("White의 폰이 세로로 있는 경우 점수를 계산한다.")
    @Test
    void Should_CalculateScore_When_WhiteVertically() {
        game.move(new Square(File.A, Rank.TWO), new Square(File.B, Rank.THREE));

        assertThat(game.calculateScore(Camp.WHITE)).isEqualTo(TWO_VERTICAL_PAWN_SCORE);
    }

    @DisplayName("Black의 폰이 세로로 있는 경우 점수를 계산한다.")
    @Test
    void Should_CalculateScore_When_BlackVertically() {
        game.move(new Square(File.A, Rank.TWO), new Square(File.B, Rank.THREE));
        game.move(new Square(File.A, Rank.SEVEN), new Square(File.B, Rank.SIX));

        assertThat(game.calculateScore(Camp.BLACK)).isEqualTo(TWO_VERTICAL_PAWN_SCORE);
    }
}
