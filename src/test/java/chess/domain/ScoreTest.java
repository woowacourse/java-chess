package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Queen;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("기물들의 점수를 잘 계산하는지 1")
    void calculateScore1() {
        Map<Position, AbstractPiece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK)); // 9
        pieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK)); // 1
        pieces.put(Position.of(Column.H, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.G, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.C, Row.RANK_6), new Pawn(Color.BLACK)); // 1

        Score score = Score.of(pieces);

        assertThat(score.getValue()).isEqualTo(12);
    }

    @Test
    @DisplayName("기물들의 점수를 잘 계산하는지 2")
    void calculateScore2() {
        Map<Position, AbstractPiece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK)); // 9
        pieces.put(Position.of(Column.C, Row.RANK_3), new King(Color.BLACK)); // 0
        pieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK)); // 1
        pieces.put(Position.of(Column.H, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.G, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.F, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.C, Row.RANK_6), new Pawn(Color.BLACK)); // 1

        Score score = Score.of(pieces);

        assertThat(score.getValue()).isEqualTo(12.5);
    }
}
