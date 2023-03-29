package chessgame.domain;

import static chessgame.domain.point.PointFixture.*;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.piece.Bishop;
import chessgame.domain.piece.King;
import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Queen;
import chessgame.domain.piece.Rook;
import chessgame.domain.point.Point;

class ScoresTest {
    @Test
    @DisplayName("각 팀이 예상하는 점수를 계산해내는지 테스트한다")
    void Should_EqualRealScoreWithExpectedScore_When_InSettingBoard() {
        // given
        Map<Point, Piece> board = new HashMap<>();
        board.put(B8, King.from(Team.BLACK));
        board.put(C8, Rook.from(Team.BLACK));
        board.put(A7, Pawn.from(Team.BLACK));
        board.put(C7, Pawn.from(Team.BLACK));
        board.put(D7, Bishop.from(Team.BLACK));
        board.put(B6, Pawn.from(Team.BLACK));
        board.put(E6, Queen.from(Team.BLACK));
        board.put(F4, Knight.from(Team.WHITE));
        board.put(G4, Queen.from(Team.WHITE));
        board.put(F3, Pawn.from(Team.WHITE));
        board.put(H3, Pawn.from(Team.WHITE));
        board.put(F2, Pawn.from(Team.WHITE));
        board.put(G2, Pawn.from(Team.WHITE));
        board.put(E1, Rook.from(Team.WHITE));
        board.put(F1, King.from(Team.WHITE));
        Game game = new Game(new Board(0, board));

        // when
        game.calculateScore();
        Score blackScore = new Score(Team.BLACK, 20);
        Score whiteScore = new Score(Team.WHITE, 19.5);

        // then
        Assertions.assertThat(game.score().get())
            .contains(blackScore, whiteScore);
    }

    @DisplayName("승자가 1명일 경우 1명만 우승하는지 알아본다.")
    @Test
    void Should_BlackWin_When_BlackHasHighScore() {
        // given
        Map<Point, Piece> board = new HashMap<>();
        board.put(B8, King.from(Team.BLACK));
        board.put(C8, Rook.from(Team.BLACK));
        board.put(F3, Pawn.from(Team.WHITE));
        board.put(F1, King.from(Team.WHITE));
        Game game = new Game(new Board(0, board));

        // when
        game.calculateScore();

        // then
        Assertions.assertThat(game.score().winner()).contains(Team.BLACK);
        Assertions.assertThat(game.score().winner().size()).isEqualTo(1);
    }

    @DisplayName("점수가 같을 경우 무승부인지 알아본다")
    @Test
    void Should_Draw_When_TwoTeamHaveSameScore() {
        // given
        Map<Point, Piece> board = new HashMap<>();
        board.put(B8, King.from(Team.BLACK));
        board.put(A7, Pawn.from(Team.BLACK));
        board.put(G2, Pawn.from(Team.WHITE));
        board.put(F1, King.from(Team.WHITE));
        Game game = new Game(new Board(0, board));

        // when
        game.calculateScore();

        // then
        Assertions.assertThat(game.score().winner()).contains(Team.BLACK, Team.WHITE);
        Assertions.assertThat(game.score().winner().size()).isEqualTo(2);
    }

}
