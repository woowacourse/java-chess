package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    void calculate_한쪽의_킹이_죽었을_때() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("e7"), new King(Team.WHITE));
        board.put(Position.of("a2"), new Pawn(Team.BLACK));
        board.put(Position.of("b4"), new Pawn(Team.WHITE));

        Score score = ScoreCalculator.calculate(board);
        assertThat(score.getWinner()).isEqualTo(Team.WHITE.toString());
    }

    @Test
    void isDraw_모든_킹이_살아있고_한_쪽의_점수가_높을_때() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("e7"), new King(Team.WHITE));
        board.put(Position.of("a2"), new Pawn(Team.WHITE));
        board.put(Position.of("e6"), new King(Team.BLACK));
        board.put(Position.of("b4"), new Bishop(Team.BLACK));

        Score score = ScoreCalculator.calculate(board);
        assertThat(score.isDraw()).isFalse();
    }

    @Test
    void isDraw_모든_킹이_살아있고_두_팀의_점수가_같을_때() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("e7"), new King(Team.WHITE));
        board.put(Position.of("a2"), new Pawn(Team.WHITE));
        board.put(Position.of("b4"), new King(Team.BLACK));
        board.put(Position.of("a4"), new Pawn(Team.BLACK));

        Score score = ScoreCalculator.calculate(board);
        assertThat(score.isDraw()).isTrue();
    }

    @Test
    void 같은팀_폰이_같은_File_에_있을_때를_고려한_점수계산() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("a1"), new Pawn(Team.WHITE));
        board.put(Position.of("a2"), new Pawn(Team.WHITE));
        board.put(Position.of("e8"), new King(Team.WHITE));

        board.put(Position.of("c7"), new Pawn(Team.BLACK));
        board.put(Position.of("a3"), new Pawn(Team.BLACK));
        board.put(Position.of("b4"), new King(Team.BLACK));

        Map<String, Double> expected = new HashMap<>();
        expected.put(Team.WHITE.toString(), 1.0);
        expected.put(Team.BLACK.toString(), 2.0);

        Score score = ScoreCalculator.calculate(board);
        assertThat(score.getScores()).isEqualTo(expected);
    }
}
