package chess.model.board.move;

import static chess.helper.PositionFixture.A7;
import static chess.helper.PositionFixture.B3;
import static chess.helper.PositionFixture.B7;
import static chess.helper.PositionFixture.C4;
import static chess.helper.PositionFixture.C6;
import static chess.helper.PositionFixture.C7;
import static chess.helper.PositionFixture.C8;
import static chess.helper.PositionFixture.D5;
import static chess.helper.PositionFixture.E4;
import static chess.helper.PositionFixture.E6;
import static chess.helper.PositionFixture.F3;
import static chess.helper.PositionFixture.F7;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.helper.ChessBoardPieceMovingHelper;
import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardFactory;
import chess.model.piece.Camp;
import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardMoveBishopTest {

    private final Position source = D5;
    private ChessBoard chessBoard;

    @BeforeEach
    void beforeEach() {
        chessBoard = ChessBoardFactory.create();
        ChessBoardPieceMovingHelper.move(chessBoard, C8, source);
    }

    @ParameterizedTest(name = "검은색 비숍은 경로에 기물이 있다면 목적지와 무관하게 유효한 방향으로 이동할 수 없다.")
    @DisplayName("move() 유효한 이동 방향, 유효한 이동 거리, 유효한 목적지, 경로 중 아군 기물을 만날 경우 테스트")
    @MethodSource("provideInValidWayPoint")
    void move_givenInvalidEnemyWayPoint_thenFail(final Position targetPosition) {
        // given
        ChessBoardPieceMovingHelper.move(chessBoard, B7, C6);
        ChessBoardPieceMovingHelper.move(chessBoard, F7, E6);
        ChessBoardPieceMovingHelper.move(chessBoard, A7, C4);
        ChessBoardPieceMovingHelper.move(chessBoard, C7, E4);

        // when, then
        assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }

    private static Stream<Arguments> provideInValidWayPoint() {
        return Stream.of(
                Arguments.of(B7), Arguments.of(F7), Arguments.of(B3), Arguments.of(F3)
        );
    }
}
