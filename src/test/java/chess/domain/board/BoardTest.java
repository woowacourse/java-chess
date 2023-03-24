package chess.domain.board;

import static chess.PositionFixtures.A2;
import static chess.PositionFixtures.A3;
import static chess.PositionFixtures.B2;
import static chess.PositionFixtures.C2;
import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new BoardFactory().createInitialBoard();
    }

    @Test
    void test_moveEmptyFrom() {
        Position emptyPosition = new Position(4, 4);

        assertThatThrownBy(() -> board.move(emptyPosition, new Position(1, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    void test_movePawn() {
        Position from = new Position(1, 2);
        Position to = new Position(1, 4);

        assertTrue(board.getBoard().containsKey(from));
        assertFalse(board.getBoard().containsKey(to));

        board.move(from, to);

        assertFalse(board.getBoard().containsKey(from));
        assertTrue(board.getBoard().containsKey(to));
    }

    @Test
    @DisplayName("보드에서 색별 점수를 계산하는 기능 추가")
    void test_calculateScore() {
        final Map<Position, Piece> boardMap = Map.of(
                A2, new Pawn(BLACK),
                B2, new Pawn(BLACK),
                C2, new Pawn(BLACK)
        );
        final Board board = new Board(boardMap);

        final Map<Color, Score> colorScoreMap = board.calculateScore();

        assertThat(colorScoreMap.get(BLACK)).isEqualTo(new Score(3));
    }

    @Test
    @DisplayName("보드에서 점수를 계산할 떄 폰이 같은 열에 있는 경우 추가")
    void calculateScoreTest() {
        final Map<Position, Piece> boardMap = Map.of(
                A2, new Pawn(BLACK),
                A3, new Pawn(BLACK)
        );

        final Board board = new Board(boardMap);

        final Map<Color, Score> colorScoreMap = board.calculateScore();

        assertThat(colorScoreMap.get(BLACK).getValue()).isEqualTo(1);
    }

}
