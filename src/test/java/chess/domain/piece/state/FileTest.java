package chess.domain.piece.state;

import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.InitialColumn;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @Test
    void calculateScoreOf() {
        Team team = Team.WHITE;
        Piece pawn = PieceFactory.createInitializedPawn(team);
        Piece rook = PieceFactory.createPieceWithInitialColumn(InitialColumn.ROOK, team);
        List<Piece> pieces = Arrays.asList(pawn, rook);
        File file = new File(pieces);
        Score score = file.calculateScoreOf(team);
        assertThat(score).isEqualTo(Score.of(6));
    }
}