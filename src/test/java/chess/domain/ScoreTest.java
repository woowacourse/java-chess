package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PiecesUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("기물들의 점수를 잘 계산하는지 1")
    void calculateScore1() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN); // 9
        pieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN); // 1
        pieces.put(Position.of(Column.H, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.G, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.C, Row.RANK_6), BLACK_PAWN); // 1

        Score score = Score.of(pieces);

        assertThat(score.getValue()).isEqualTo(12);
    }

    @Test
    @DisplayName("기물들의 점수를 잘 계산하는지 2")
    void calculateScore2() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN); // 9
        pieces.put(Position.of(Column.C, Row.RANK_3), BLACK_KING); // 0
        pieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN); // 1
        pieces.put(Position.of(Column.H, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.G, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.F, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.C, Row.RANK_6), BLACK_PAWN); // 1

        Score score = Score.of(pieces);

        assertThat(score.getValue()).isEqualTo(12.5);
    }
}
