package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    @Nested
    class 점수계산 {
        @Test
        void should_38점을반환한다_when_초기상태일때() {
            //given
            ChessBoard chessBoard = ChessBoardFactory.create();
            List<Square> squares = chessBoard.getSquares();
            final double expected = 38.0;

            //when
            final double whiteScore = ScoreCalculator.calculateScore(squares, Team.WHITE);
            final double blackScore = ScoreCalculator.calculateScore(squares, Team.BLACK);

            //then
            assertThat(whiteScore).isEqualTo(blackScore).isEqualTo(expected);
        }

        @Test
        void should_1점을반환한다_when_2개폰이한줄에있을때() {
            //given
            Square square = new Square(Position.of(File.F, Rank.FIVE), new Pawn(Team.WHITE));
            Square square2 = new Square(Position.of(File.F, Rank.SIX), new Pawn(Team.WHITE));
            List<Square> squares = List.of(square, square2);
            final double expected = 1;

            //when
            final double actual = ScoreCalculator.calculateScore(squares, Team.WHITE);

            //then
            assertThat(actual).isEqualTo(expected);
        }

    }
}
