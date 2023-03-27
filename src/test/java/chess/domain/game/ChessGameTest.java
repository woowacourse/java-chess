package chess.domain.game;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Score;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    @Test
    void 체스게임은_각_팀의_점수를_계산할_수_있다() {
        //given
        final ChessGame game = new ChessGame();

        final Coordinate from1 = Coordinate.from("a2");
        final Coordinate to1 = Coordinate.from("a4");

        final Coordinate from2 = Coordinate.from("a7");
        final Coordinate to2 = Coordinate.from("a5");

        final Coordinate from3 = Coordinate.from("b2");
        final Coordinate to3 = Coordinate.from("b4");

        final Coordinate from4 = Coordinate.from("a5");
        final Coordinate to4 = Coordinate.from("b4");

        //when
        game.start();
        game.move(from1, to1);
        game.move(from2, to2);
        game.move(from3, to3);
        game.move(from4, to4);

        //then
        final Score black = game.getScore().get(Team.BLACK);
        final Score white = game.getScore().get(Team.WHITE);

        assertSoftly(softly -> {
            softly.assertThat(black).isEqualTo(Score.from(37.5));
            softly.assertThat(white).isEqualTo(Score.from(37));
        });
    }
}
