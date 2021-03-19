package chess.domain.board;

import chess.domain.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("다른 팀 말을 움직이려고 하면, 예외가 발생한다.")
    void anotherTeamPieceMoveCheck() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.getBoard();

        Position startPoint = new Position(6, 0);
        Position endPoint = new Position(6, 1);

        assertThatThrownBy(
                () -> board.move(startPoint, endPoint, Team.BLACK)
        ).isInstanceOf(IllegalArgumentException.class);
        board.move(startPoint, endPoint, Team.WHITE);
    }
}