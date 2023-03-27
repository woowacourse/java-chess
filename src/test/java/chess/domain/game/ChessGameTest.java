package chess.domain.game;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.domain.piece.Score;
import chess.domain.piece.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    @Test
    void 체스게임은_각_팀의_점수를_계산할_수_있다() {
        //given
        final ChessGame game = new ChessGame();
        final Command start = new Command(CommandType.START, List.of());
        final Command command1 = new Command(CommandType.MOVE, List.of("a2", "a4"));
        final Command command2 = new Command(CommandType.MOVE, List.of("a7", "a5"));
        final Command command3 = new Command(CommandType.MOVE, List.of("b2", "b4"));
        final Command command4 = new Command(CommandType.MOVE, List.of("a5", "b4"));

        //when
        game.execute(start);
        game.execute(command1);
        game.execute(command2);
        game.execute(command3);
        game.execute(command4);

        //then
        final Score black = game.getScore().get(Team.BLACK);
        final Score white = game.getScore().get(Team.WHITE);

        assertSoftly(softly -> {
            softly.assertThat(black).isEqualTo(Score.from(37.5));
            softly.assertThat(white).isEqualTo(Score.from(37));
        });
    }
}
