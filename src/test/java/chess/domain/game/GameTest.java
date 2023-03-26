package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.TeamNotMatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("내 차례가 아닐 경우 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_NotMyTurn() {
        Game game = new Game();

        Square source = new Square(File.A, Rank.SEVEN);
        Square target = new Square(File.A, Rank.SIX);

        assertThatThrownBy(() -> game.move(source, target))
                .isInstanceOf(TeamNotMatchException.class)
                .hasMessage("WHITE팀의 말을 선택해주세요.");
    }

    @DisplayName("내 차례일 경우 움직일 수 있다.")
    @Test
    void Should_Success_When_MyTurn() {
        Game game = new Game();

        Square source = new Square(File.A, Rank.TWO);
        Square target = new Square(File.A, Rank.THREE);

        assertDoesNotThrow(() -> game.move(source, target));
    }

    @DisplayName("보드 상의 모든 말을 가져올 수 있다.")
    @Test
    void Should_SizeIs64_When_GetPieces() {
        Game game = new Game();

        assertThat(game.getPieces()).hasSize(64);
    }

    @DisplayName("King이 잡히면 게임이 끝난다.")
    @Test
    void Should_GameEnd_When_CheckKing() {
        Game game = new Game();

        game.move(new Square(File.E, Rank.TWO), new Square(File.E, Rank.FOUR));
        game.move(new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FIVE));
        game.move(new Square(File.E, Rank.ONE), new Square(File.E, Rank.TWO));
        game.move(new Square(File.D, Rank.FIVE), new Square(File.D, Rank.FOUR));
        game.move(new Square(File.E, Rank.TWO), new Square(File.E, Rank.THREE));
        game.move(new Square(File.D, Rank.FOUR), new Square(File.E, Rank.THREE));

        assertThat(game.isGameEnd()).isTrue();
    }

    @DisplayName("King이 잡힌게 아니면 게임이 계속된다.")
    @Test
    void Should_GameContinue_When_NotCheckKing() {
        Game game = new Game();

        game.move(new Square(File.E, Rank.TWO), new Square(File.E, Rank.FOUR));
        game.move(new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FIVE));
        game.move(new Square(File.E, Rank.ONE), new Square(File.E, Rank.TWO));
        game.move(new Square(File.D, Rank.FIVE), new Square(File.D, Rank.FOUR));
        game.move(new Square(File.E, Rank.TWO), new Square(File.E, Rank.THREE));

        assertThat(game.isGameEnd()).isFalse();
    }

    @DisplayName("게임이 시작하자마자 끝났을 때, 점수는 38점이다.")
    @Test
    void Should_WhiteScoreIs47_When_GameEnd() {
        Game game = new Game();

        assertThat(game.calculateWhiteScore()).isEqualTo(38);
    }

    @DisplayName("게임이 시작하자마자 끝났을 때, 점수는 38점이다.")
    @Test
    void Should_BlackScoreIs47_When_GameEnd() {
        Game game = new Game();

        assertThat(game.calculateBlackScore()).isEqualTo(38);
    }
}
