package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

class ScoreTest {

    @ParameterizedTest
    @DisplayName("보드의 상황에 따라 점수를 잘 계산하는지 확인한다")
    @EnumSource(value = Color.class, names = {"BLACK", "WHITE"})
    void calculateScore(Color color) {
        Score scoreCalculator = new Score(BoardFactory.getInitialPieces());
        Map<Color, Double> score = scoreCalculator.getAllTeamScore();

        assertThat(score.get(color)).isEqualTo(38);
    }

    @Test
    @DisplayName("같은 줄에 폰을 가지고 있을 경우 점수를 잘 계산하는지 확인한다")
    void calculatePawnSameRowScore() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(File.A, Rank.ONE), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(File.A, Rank.TWO), new Pawn(Color.WHITE));

        Score scoreCalculator = new Score(initialPieces);
        Map<Color, Double> score = scoreCalculator.getAllTeamScore();

        assertThat(score.get(Color.WHITE)).isEqualTo(1);
    }

    @Test
    @DisplayName("같은 줄에 폰을 가지고 있는게 여러 개인 경우 점수를 잘 계산하는지 확인한다")
    void calculatePawnSameRowScore2() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(File.A, Rank.ONE), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(File.A, Rank.TWO), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(File.B, Rank.ONE), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(File.B, Rank.TWO), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(File.B, Rank.FOUR), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(File.C, Rank.TWO), new Pawn(Color.WHITE));

        Score scoreCalculator = new Score(initialPieces);
        Map<Color, Double> score = scoreCalculator.getAllTeamScore();

        assertThat(score.get(Color.WHITE)).isEqualTo(3.5);
    }
}