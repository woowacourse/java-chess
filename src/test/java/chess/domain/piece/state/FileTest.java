package chess.domain.piece.state;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @Test
    void calculateScoreOf() {
        Team team = Team.WHITE;
        List<Piece> pieces = Arrays.asList(new Pawn(team), new Rook(team));
        File file = new File(pieces);
        Score score = file.calculateScoreOf(team);
        assertThat(score).isEqualTo(Score.of(6));
    }
}