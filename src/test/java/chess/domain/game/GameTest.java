package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.TeamNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("내 차례가 아닐 경우 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_NotMyTurn() {
        final Game game = new Game();

        final Square source = new Square(File.A, Rank.SEVEN);
        final Square target = new Square(File.A, Rank.SIX);

        assertThatThrownBy(() -> game.move(source, target))
                .isInstanceOf(TeamNotMatchException.class)
                .hasMessage("WHITE팀의 말을 선택해주세요.");
    }

    @DisplayName("내 차례일 경우 움직일 수 있다.")
    @Test
    void Should_Success_When_MyTurn() {
        final Game game = new Game();

        final Square source = new Square(File.A, Rank.TWO);
        final Square target = new Square(File.A, Rank.THREE);

        Assertions.assertDoesNotThrow(() -> game.move(source, target));
    }

    @DisplayName("보드 상의 모든 말을 가져올 수 있다.")
    @Test
    void Should_SizeIs64_When_GetPieces() {
        final Game game = new Game();

        assertThat(game.getPieces()).hasSize(64);
    }
}
