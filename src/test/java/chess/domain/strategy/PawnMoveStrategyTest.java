package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.BoardFactory;
import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnMoveStrategyTest {

    @Test
    @DisplayName("현재 말의 게임 턴이 아니면 예외가 발생한다.")
    void moveByNotTurnPiece() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(new BoardFactory().getInitialBoard());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> pawnMoveStrategy.move(Color.BLACK, new Position(2, 2), new Position(2, 3)))
                .withMessage("상대 말은 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("처음에는 두 칸 이동할 수 있다.")
    void moveByUpUp() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(new BoardFactory().getInitialBoard());
        pawnMoveStrategy.move(Color.WHITE, new Position(2, 2), new Position(2, 4));

        assertAll(
                () -> assertThat(pawnMoveStrategy.collectBoard().get(new Position(2, 4))).isEqualTo(PieceType.WHITE_PAWN),
                () -> assertThat(pawnMoveStrategy.collectBoard().get(new Position(2, 2))).isEqualTo(PieceType.BLANK)
        );
    }

    @Test
    @DisplayName("폰은 대각선에 상대말이 없으면 이동할 수 없다.")
    void moveByRightUpAndLeftUpBlank() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(new BoardFactory().getInitialBoard());
        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawnMoveStrategy.move(Color.WHITE, new Position(2, 2), new Position(3, 3)))
                        .withMessage("이동 할 수 없는 위치입니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawnMoveStrategy.move(Color.WHITE, new Position(2, 2), new Position(1, 3)))
                        .withMessage("이동 할 수 없는 위치입니다.")
        );
    }
}