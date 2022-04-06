package chess.domain;

import chess.domain.board.BoardCache;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @ParameterizedTest
    @DisplayName("전체 점수 계산 확인")
    @CsvSource(value = {"WHITE:38", "BLACK:38"}, delimiter = ':')
    void computeScore(Color color, int score) {
        Map<Position, Piece> boardCache = BoardCache.create();

        assertThat(Score.create(boardCache, color).getScore()).isEqualTo(score);
        assertThat(Score.create(boardCache, color).getScore()).isEqualTo(score);
    }

    @Test
    @DisplayName("king 점수 계산 확인")
    void checkKingScore() {
        Map<Position, Piece> boardCache = Map.of(new Position(0, 4), new King(Color.WHITE));

        assertThat(Score.create(boardCache, Color.WHITE).getScore()).isEqualTo(0);
    }
}