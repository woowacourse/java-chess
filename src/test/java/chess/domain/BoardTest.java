package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.init();
    }

    @Test
    @DisplayName("source 위치에 기물이 없으면 예외가 발생한다.")
    void validateNothingInSource() {
        Position source = new Position(3, 4);
        Position target = new Position(4, 4);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] source 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("source 위치의 기물이 target 위치로 이동할 수 없으면 예외가 발생한다.")
    void validateUnmovable() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 4);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 경로에 기물이 있으면 예외가 발생한다.")
    void validateBlocked() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 1);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동 경로에 기물이 있습니다.");
    }

    @Test
    @DisplayName("폰이 대각선으로 이동할 경우, target 위치에 상대팀 기물이 없으면 예외가 발생한다.")
    void validatePawnMoveCrossWhenThereIsNothingInTarget() {
        Position source = new Position(2, 1);
        Position target = new Position(3, 2);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 상대 기물이 없을 경우, 대각선으로 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("폰이 대각선으로 이동할 경우, target 위치에 상대팀 기물이 없으면 예외가 발생한다.")
    void validateOurTeamInTarget() {
        Position pawnSource = new Position(2, 1);
        Position pawnTarget = new Position(4, 1);
        Position rookSource = new Position(1, 1);
        Position rookTarget = new Position(4, 1);

        board.move(pawnSource, pawnTarget);
        assertThatThrownBy(() -> board.move(rookSource, rookTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @Test
    @DisplayName("폰이 일직선으로 이동할 경우, target 위치에 기물이 있으면 예외가 발생한다.")
    void validatePawnMoveStraightWhenThereIsSomethingInTarget() {
        Position knightFirstSource = new Position(8, 2);
        Position knightFirstTarget = new Position(6, 3);
        Position knightSecondTarget = new Position(4, 4);
        board.move(knightFirstSource, knightFirstTarget);
        board.move(knightFirstTarget, knightSecondTarget);

        Position pawnSource = new Position(2, 4);
        Position pawnTarget = new Position(4, 4);

        assertThatThrownBy(() -> board.move(pawnSource, pawnTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 기물이 있는 곳으로 직진할 수 없습니다.");
    }
}
