package chess.domain.board;

import chess.domain.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.getBoard();
    }

    @Test
    @DisplayName("다른 팀 말을 움직이려고 하면, 예외가 발생한다.")
    void anotherTeamPieceMoveCheck() {
        Position startPoint = new Position(6, 0);
        Position endPoint = new Position(4, 0);

        assertThatThrownBy(
                () -> board.move(startPoint, endPoint, Team.BLACK)
        ).isInstanceOf(IllegalArgumentException.class);
        board.move(startPoint, endPoint, Team.WHITE);
    }

    @Test
    @DisplayName("보드 크기를 벗어나는 좌표로 이동하려고 하면, false를 리턴한다.")
    void validateRangeCheck() {
        assertAll(
                () -> assertFalse(board.validateRange(8, 0)),
                () -> assertFalse(board.validateRange(-1, 0)),
                () -> assertFalse(board.validateRange(0, 8)),
                () -> assertFalse(board.validateRange(0, -1))
        );
    }
}