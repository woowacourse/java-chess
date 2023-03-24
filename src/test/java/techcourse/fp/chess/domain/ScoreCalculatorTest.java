package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PieceFixtures.BLACK_QUEEN;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_QUEEN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.C1;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.Piece;

class ScoreCalculatorTest {

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private Map<Position, Piece> emptyBoard;

    @BeforeEach
    void generate() {
        emptyBoard = createEmptyBoard();
    }

    @DisplayName("기물의 점수를 정상적으로 계산한다.")
    @Test
    void calculate_score() {
        //given
        emptyBoard.put(A1, WHITE_QUEEN);
        //when
        final double actual = scoreCalculator.calculate(emptyBoard, Color.WHITE);
        //then
        assertThat(actual).isEqualTo(9.0);
    }

    @DisplayName("지정한 색의 기물들만 점수를 계산한다")
    @Test
    void calculate_specific_color() {
        //given
        emptyBoard.put(A1, BLACK_QUEEN);
        emptyBoard.put(A2, WHITE_QUEEN);
        emptyBoard.put(A3, WHITE_QUEEN);
        //when
        final double actual = scoreCalculator.calculate(emptyBoard, Color.WHITE);
        //then
        assertThat(actual).isEqualTo(18.0);
    }

    @DisplayName("초기 상태의 보드에서 각 진영의 점수는 38점이다.")
    @Test
    void calculate_default_board_score() {
        //given
        final Board startBoard = BoardFactory.generate();
        final Map<Position, Piece> board = startBoard.getBoard();
        //when
        final double actual = scoreCalculator.calculate(board, Color.WHITE);
        //then
        assertThat(actual).isEqualTo(38.0);
    }

    @DisplayName("폰의 점수는 기본적으로 1점이다.")
    @Test
    void calculate_ordinal_pawn_score() {
        //given
        emptyBoard.put(A1, WHITE_PAWN);
        emptyBoard.put(B1, WHITE_PAWN);
        emptyBoard.put(C1, WHITE_PAWN);
        //when
        final double actual = scoreCalculator.calculate(emptyBoard, Color.WHITE);
        //then
        assertThat(actual).isEqualTo(3);
    }

    @DisplayName("폰이 세로로 겹쳐 있는 경우에는 같은 각 0.5점으로 계산한다.")
    @Test
    void calculate_multiple_() {
        //given
        emptyBoard.put(A1, WHITE_PAWN);
        emptyBoard.put(A2, WHITE_PAWN);
        emptyBoard.put(A3, WHITE_PAWN);
        emptyBoard.put(B1, WHITE_PAWN);
        //when
        final double actual = scoreCalculator.calculate(emptyBoard, Color.WHITE);
        //then
        assertThat(actual).isEqualTo(2.5);
    }

    private Map<Position, Piece> createEmptyBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.create());
            }
        }

        return board;
    }
}
