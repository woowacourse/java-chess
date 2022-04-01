package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.board.Point;
import chess.domain.board.TestBoardGenerator;

class PieceTypeTest {

    @Test
    @DisplayName("점수를 계산할 수 있다.")
    void calculateScoreTest() {
        double initialTotalScore = 38;

        InitialBoardGenerator generator = new InitialBoardGenerator();
        Map<Point, Piece> pointPieces = generator.generate();

        assertThat(PieceType.calculateScore(pointPieces, Color.WHITE))
            .isEqualTo(initialTotalScore);
    }

    @Test
    @DisplayName("폰이 세로줄에 두개 이상 존재할 경우 0.5점으로 계산된다.")
    void calculateScoreWithVerticalPawn() {
        double blackScore = 6;
        TestBoardGenerator generator = new TestBoardGenerator(Map.of(
            Point.of("a7"), new Pawn(Color.BLACK),
            Point.of("a6"), new Pawn(Color.BLACK),
            Point.of("a8"), new Rook(Color.BLACK)
        ));
        Map<Point, Piece> pointPieces = generator.generate();

        assertThat(PieceType.calculateScore(pointPieces, Color.BLACK))
            .isEqualTo(blackScore);
    }
}
