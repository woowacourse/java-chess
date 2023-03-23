package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ScoreTest {

    @Test
    void 기물들의_점수를_계산할_수_있다() {
        // given
        final Board board = BoardFactory.create();
        final Score score = new Score(board.getBoard());

        // when
        final double blackScore = score.calculatePoints(Color.BLACK);

        // then
        Assertions.assertThat(blackScore).isEqualTo(38.0);
    }

    @Test
    void 폰이_세로줄에_여러_개_있는_경우_0_5점으로_계산한다() {
        // given
        final Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(File.A, Rank.ONE), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.TWO), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.THREE), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.FOUR), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.FIVE), Pawn.from(Color.WHITE));
        final Score score = new Score(board);

        // when
        final double points = score.calculatePoints(Color.BLACK);

        // then
        Assertions.assertThat(points).isEqualTo(2);
    }


}