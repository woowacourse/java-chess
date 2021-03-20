package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("기물들에게 주어진 점수에 따라, 알맞은 계산을 한다.")
    void calculate_score() {
        final Score score = new Score();
        final Map<Position, Piece> piecePosition = new HashMap<>();
        piecePosition.put(Position.of("a1"), new Rook());
        piecePosition.put(Position.of("a2"), new Bishop());
        piecePosition.put(Position.of("a3"), new Knight());
        piecePosition.put(Position.of("a4"), new Queen());

        assertThat(score.calculateScore(piecePosition)).isEqualTo(19.5);
    }

    @Test
    @DisplayName("세로축에 폰이 하나 밖에 없으면 개당 1점, 하나 이상이라면 개당 0.5점으로 계산한다.")
    void calculate_pawn_score() {
        final Score score = new Score();
        final Map<Position, Piece> piecePosition = new HashMap<>();
        piecePosition.put(Position.of("a1"), new Pawn(1));
        assertThat(score.calculateScore(piecePosition)).isEqualTo(1.0);

        piecePosition.put(Position.of("a2"), new Pawn(1));
        assertThat(score.calculateScore(piecePosition)).isEqualTo(1.0);

        piecePosition.put(Position.of("a3"), new Pawn(1));
        assertThat(score.calculateScore(piecePosition)).isEqualTo(1.5);
    }
}
