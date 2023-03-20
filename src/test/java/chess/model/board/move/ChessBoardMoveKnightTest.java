package chess.model.board.move;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static chess.helper.PositionFixture.A4;
import static chess.helper.PositionFixture.A5;
import static chess.helper.PositionFixture.A7;
import static chess.helper.PositionFixture.B1;
import static chess.helper.PositionFixture.B4;
import static chess.helper.PositionFixture.B5;
import static chess.helper.PositionFixture.B8;
import static chess.helper.PositionFixture.C2;
import static chess.helper.PositionFixture.D4;
import static chess.helper.PositionFixture.E2;
import static chess.helper.PositionFixture.F1;
import static chess.helper.PositionFixture.H2;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.helper.ChessBoardPieceMovingHelper;
import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardFactory;
import chess.model.piece.Camp;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessBoardMoveKnightTest {

    @Nested
    @DisplayName("move() 검은색 나이트 테스트")
    class BlackKnightTest {

        private final Position source = D4;
        private ChessBoard chessBoard;

        @BeforeEach
        void beforeEach() {
            chessBoard = ChessBoardFactory.create();
            ChessBoardPieceMovingHelper.move(chessBoard, B8, source);
        }

        @ParameterizedTest(name = "검은색 나이트는 경로와 상관없이 목적지가 빈 칸일 때 ({0}, {1}) 방향으로 이동할 수 있다.")
        @DisplayName("move() 유효한 이동 방향, 유효한 이동 거리, 빈 칸 테스트")
        @CsvSource(value = {
                "E:SIXTH", "C:SIXTH", "F:FIFTH", "F:THIRD", "C:SECOND", "E:SECOND", "B:THIRD", "B:FIFTH"
        }, delimiter = ':')
        void move_givenValidAllAndEmptyTarget_thenSuccess(final File targetFile, final Rank targetRank) {
            // given
            ChessBoardPieceMovingHelper.move(chessBoard, C2, A3);
            ChessBoardPieceMovingHelper.move(chessBoard, E2, A4);

            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.BLACK)).doesNotThrowAnyException();
        }

        @ParameterizedTest(name = "검은색 나이트는 경로에 아무것도 없고 목적지가 적군일 때 ({0}, {1}) 방향으로 이동할 수 있다.")
        @DisplayName("move() 유효한 이동 방향, 유효한 이동 거리, 유효한 경로, 빈 칸 테스트")
        @CsvSource(value = {
                "E:SIXTH", "C:SIXTH", "F:FIFTH", "F:THIRD", "C:SECOND", "E:SECOND", "B:THIRD", "B:FIFTH"
        }, delimiter = ':')
        void move_givenValidAllAndEnemyTarget_thenSuccess(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A1, targetPosition);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.BLACK)).doesNotThrowAnyException();
        }

        @ParameterizedTest(name = "검은색 나이트는 경로에 아무것도 없고 목적지가 아군일 때 ({0}, {1}) 방향으로 이동할 수 없다.")
        @DisplayName("move() 유효한 이동 방향, 유효한 이동 거리, 유효한 경로, 아군 칸 테스트")
        @CsvSource(value = {
                "E:SIXTH", "C:SIXTH", "F:FIFTH", "F:THIRD", "C:SECOND", "E:SECOND", "B:THIRD", "B:FIFTH"
        }, delimiter = ':')
        void move_givenValidAllAndAllyTarget_thenSuccess(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A7, targetPosition);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "검은색 나이트는 목적지와 상관 없이 이동 거리가 유효하지 않은 ({0}, {1}) 방향으로 이동할 수 없다.")
        @DisplayName("move() 유효한 이동 방향, 유효하지 않은 이동 거리, 유효한 경로 테스트")
        @CsvSource(value = {
                "B:SEVENTH", "F:SEVENTH", "B:FIRST", "F:FIRST", "A:THIRD", "H:THIRD", "A:SIXTH", "G:SIXTH"
        }, delimiter = ':')
        void move_givenInvalidDistanceAndEmptyTarget_thenSuccess(final File targetFile, final Rank targetRank) {
            // given
            ChessBoardPieceMovingHelper.move(chessBoard, B1, A3);
            ChessBoardPieceMovingHelper.move(chessBoard, F1, A4);
            ChessBoardPieceMovingHelper.move(chessBoard, A3, A5);
            ChessBoardPieceMovingHelper.move(chessBoard, A7, B4);
            ChessBoardPieceMovingHelper.move(chessBoard, H2, B5);

            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("방향을 찾을 수 없습니다.");
        }

        @ParameterizedTest(name = "검은색 나이트는 유효하지 않은 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "D:FIFTH", "C:FOURTH", "D:THIRD", "E:FOURTH", "C:FIFTH", "E:FIFTH", "C:THIRD", "E:THIRD"
        }, delimiter = ':')
        void move_InvalidDirection_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
