package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("아래와 같은 pawn의 이동 케이스를 판단한다.")
class PawnMoveValidatorTest {

    Map<Position, Piece> setupBoard = Map.of(
            Position.of(2, 4), new Pawn(Color.WHITE),
            Position.of(3, 5), new Rook(Color.BLACK),
            Position.of(4, 4), new Rook(Color.BLACK));

    PawnMoveValidator pawnMoveValidator = new PawnMoveValidator(setupBoard);

    @Test
    @DisplayName("전진할 때 이동 경로에 말이 없으면 예외가 발생하지 않는다.")
    void pawnMoveForwardTest_Success() {
        // given
        Position start = Position.of(2, 4);
        Position end = Position.of(3, 4);

        // expect
        assertThatNoException()
                .isThrownBy(() -> pawnMoveValidator.checkPawnCanMove(start, end));
    }

    @Test
    @DisplayName("전진할 때 목적 좌표에 상대 말이 존재해도 예외가 발생한다.")
    void pawnMoveForwardTest_Fail() {
        // given
        Position start = Position.of(2, 4);
        Position end = Position.of(4, 4);

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pawnMoveValidator.checkPawnCanMove(start, end))
                .withMessage("[ERROR] 폰은 전진할 때 말이 없는 곳으로만 전진할 수 있습니다.");
    }

    @Test
    @DisplayName("대각선으로 이동할 때 이동 경로에 상대의 말이 존재하면 예외가 발생하지 않는다.")
    void pawnMoveDiagonalTest_Success() {
        // given
        Position start = Position.of(2, 4);
        Position end = Position.of(3, 5);

        // expect
        assertThatNoException()
                .isThrownBy(() -> pawnMoveValidator.checkPawnCanMove(start, end));
    }

    @Test
    @DisplayName("대각선으로 이동할 때 이동 경로에 상대의 말이 존재하지 않으면 예외가 발생한다.")
    void pawnMoveDiagonalTest_Fail() {
        // given
        Position start = Position.of(2, 4);
        Position end = Position.of(3, 3);

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pawnMoveValidator.checkPawnCanMove(start, end))
                .withMessage("[ERROR] 폰은 대각선 이동 경로에 말이 없으면 이동이 불가능합니다.");
    }
}