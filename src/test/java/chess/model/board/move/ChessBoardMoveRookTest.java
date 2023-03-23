package chess.model.board.move;

import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A8;
import static chess.helper.PositionFixture.B2;
import static chess.helper.PositionFixture.B5;
import static chess.helper.PositionFixture.C2;
import static chess.helper.PositionFixture.C5;
import static chess.helper.PositionFixture.D2;
import static chess.helper.PositionFixture.D3;
import static chess.helper.PositionFixture.D4;
import static chess.helper.PositionFixture.D5;
import static chess.helper.PositionFixture.D6;
import static chess.helper.PositionFixture.D7;
import static chess.helper.PositionFixture.E5;
import static chess.helper.PositionFixture.F5;
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

class ChessBoardMoveRookTest {

    private final Position source = D5;
    private ChessBoard chessBoard;

    @BeforeEach
    void beforeEach() {
        chessBoard = ChessBoardFactory.create();
        ChessBoardPieceMovingHelper.move(chessBoard, A8, source);
    }

    @ParameterizedTest(name = "검은색 룩은 경로에 기물이 있다면 목적지와 무관하게 이동할 수 없다.")
    @DisplayName("move() 유효한 이동 방향, 유효한 이동 거리, 유효한 목적지, 경로 중 기물을 만날 경우 테스트")
    @MethodSource("provideInvalidTarget")
    void move_givenInvalidAllyWayPoint_thenFail(final Position targetPosition) {
        // given
        ChessBoardPieceMovingHelper.move(chessBoard, A2, D6);
        ChessBoardPieceMovingHelper.move(chessBoard, B2, C5);
        ChessBoardPieceMovingHelper.move(chessBoard, C2, D4);
        ChessBoardPieceMovingHelper.move(chessBoard, D2, E5);

        // when, then
        assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }

    private static Stream<Arguments> provideInvalidTarget() {
        return Stream.of(
                Arguments.of(D7), Arguments.of(B5), Arguments.of(D3), Arguments.of(F5)
        );
    }
}
