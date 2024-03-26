package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("실패: 출발점에 말이 없으면 이동 불가")
    void move_NoPieceAtSourcePosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = Position.of(4, 4);
        Position targetPosition = Position.of(4, 5);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    @DisplayName("실패: 말의 규칙에 맞지 않는 위치로 이동")
    void move_IllegalMove() {
        Position sourcePosition = Position.of(2, 1);
        Position targetPosition = Position.of(3, 4);
        Board board = Board.generatedBy(new InitialBoardGenerator());

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("말의 규칙에 맞지 않는 이동입니다.");
    }
}
