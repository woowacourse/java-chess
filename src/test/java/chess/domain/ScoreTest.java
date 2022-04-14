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
    @DisplayName("King 이 없는 경우 점수는 0점이다.")
    void calculateScoreWithOut() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN);
        pieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN);
        pieces.put(Position.of(Column.H, Row.RANK_3), BLACK_PAWN);
        pieces.put(Position.of(Column.G, Row.RANK_3), BLACK_PAWN);
        pieces.put(Position.of(Column.C, Row.RANK_6), BLACK_PAWN);

        Score score = Score.of(pieces);

        assertThat(score.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("기물들의 점수를 계산하는 경우")
    void calculateScore() {
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
