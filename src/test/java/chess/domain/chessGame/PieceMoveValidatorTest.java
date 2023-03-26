package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PieceMoveValidatorTest {

    @Nested
    @DisplayName("아래와 같은 경우 예외가 발생하지 않는다.")
    class movePieceTest_Success {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 2), new Rook(Color.WHITE),
                Position.of(5, 2), new Rook(Color.BLACK));

        PieceMoveValidator pieceMoveValidator = new PieceMoveValidator(setupBoard);

        @Test
        @DisplayName("목표 좌표에 말이 없고 경로 상에 다른 말이 없으면 예외가 발생하지 않는다.")
        void moveToBlankTest() {
            // given
            Position start = Position.of(2, 2);
            Position end = Position.of(4, 2);

            // expect
            assertThatNoException()
                    .isThrownBy(() -> pieceMoveValidator.checkPieceCanMove(start, end));
        }

        @Test
        @DisplayName("목표 좌표에 상대 말이 있고 경로 상에 다른 말이 없으면 이동시킬 수 있다.")
        void moveToDifferentColorPieceTest() {
            // given
            Position start = Position.of(2, 2);
            Position end = Position.of(5, 2);

            // expect
            assertThatNoException()
                    .isThrownBy(() -> pieceMoveValidator.checkPieceCanMove(start, end));

        }
    }

    @Nested
    @DisplayName("아래와 같은 경우 예외가 발생한다.")
    class movePieceTest_Fail {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 2), new Rook(Color.WHITE),
                Position.of(4, 2), new Rook(Color.BLACK),
                Position.of(6, 2), new Rook(Color.BLACK));

        PieceMoveValidator pieceMoveValidator = new PieceMoveValidator(setupBoard);

        @Test
        @DisplayName("출발 좌표에 말이 존재하지 않는 경우 예외가 발생한다.")
        void noPieceInStartTest() {
            // given
            Position start = Position.of(3, 2);

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> pieceMoveValidator.checkPieceExistInStartPosition(start))
                    .withMessage("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }

        @Test
        @DisplayName("이동할 차례가 아닌 색의 말을 이동시키면 예외가 발생한다.")
        void notMyTurnTest() {
            // given
            Position start = Position.of(4, 2);

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> pieceMoveValidator.checkTurn(start, Color.WHITE))
                    .withMessage("[ERROR] WHITE의 차례입니다.");
        }

        @Test
        @DisplayName("출발 좌표에 있는 말의 이동 로직에 부합하지 않는 경로인 경우 예외가 발생한다.")
        void notMatchToPieceMovingLogicTest() {
            // given
            Position start = Position.of(2,2);
            Position end = Position.of(5,5);

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> pieceMoveValidator.checkPieceCanMove(start, end))
                            .withMessage("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
        }

        @Test
        @DisplayName("진행 경로 상에 말이 있는 경우 예외가 발생한다.")
        void blockedTest() {
            // given
            Position start = Position.of(2, 2);
            Position end = Position.of(5, 2);

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> pieceMoveValidator.checkPieceCanMove(start, end))
                            .withMessage("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
        }

        @Test
        @DisplayName("목표 좌표에 자신의 말이 존재하는 경우 예외가 발생한다.")
        void existSameColorPieceInEndTest() {
            // given
            Position start = Position.of(4, 2);
            Position end = Position.of(6, 2);

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> pieceMoveValidator.checkPieceCanMove(start, end))
                    .withMessage("[ERROR] 목표 좌표에 같은 색 말이 있으면 이동이 불가능합니다.");
        }
    }
}