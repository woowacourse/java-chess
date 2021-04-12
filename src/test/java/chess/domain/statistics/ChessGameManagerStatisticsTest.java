package chess.domain.statistics;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.board.TestBoardInitializer;
import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameManagerStatisticsTest {
    @DisplayName("기본 점수 38점이 잘 계산되는지 확인")
    @Test
    void defaultScoreTest() {
        Map<Color, Double> colorsScore = ChessGameStatistics.createNotStartGameResult().getColorsScore();
        assertThat(colorsScore.get(Color.BLACK)).isEqualTo(38.0);
        assertThat(colorsScore.get(Color.WHITE)).isEqualTo(38.0);
    }

    @DisplayName("말 갯수에 따라 점수계산이 잘 되는지 확인")
    @Test
    void calculateTest() {
        List<Square> squares = Arrays.asList(
                new Square(Position.of("a1"), new Queen(Color.WHITE)), // 9
                new Square(Position.of("a2"), new Rook(Color.WHITE)), // 5
                new Square(Position.of("a3"), new Pawn(Color.WHITE)), // 1
                new Square(Position.of("a4"), new Bishop(Color.WHITE)), // 3


                new Square(Position.of("b1"), new Pawn(Color.BLACK)), // 0.5
                new Square(Position.of("b2"), new Pawn(Color.BLACK)), // 0.5
                new Square(Position.of("b3"), new Pawn(Color.BLACK)), // 0.5
                new Square(Position.of("b4"), new Pawn(Color.BLACK)), // 0.5
                new Square(Position.of("c4"), new Pawn(Color.BLACK)), // 1
                new Square(Position.of("b5"), new Knight(Color.BLACK)) // 2.5
        );
        Board board = TestBoardInitializer.createTestBoard(squares);
        assertThat(board.getScoreMap().get(Color.WHITE)).isEqualTo(1 + 3 + 5 + 9);
        assertThat(board.getScoreMap().get(Color.BLACK)).isEqualTo((0.5 * 4) + 1 + 2.5);
    }
}