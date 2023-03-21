package chess.model.board.move;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A8;
import static chess.helper.PositionFixture.D2;
import static chess.helper.PositionFixture.D7;
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

public class ChessBoardMoveInitialPawnTest {

    @Nested
    @DisplayName("move() 검은색 폰 테스트")
    class BlackPawnTest {

        private final Position source = D7;
        private ChessBoard chessBoard;

        @BeforeEach
        void beforeEach() {
            chessBoard = ChessBoardFactory.create();
        }

        @ParameterizedTest(name = "검은색 폰은 유효하지 않은 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "D:EIGHTH", "C:EIGHTH", "C:SEVENTH", "C:SIXTH", "E:SIXTH", "E:SEVENTH", "E:EIGHTH"
        }, delimiter = ':')
        void move_givenInvalidDirectionAndEmptyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "검은색 폰은 목적지가 아군일 때 모든 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 모든 이동 방향, 아군 테스트")
        @CsvSource(value = {
                "D:EIGHTH", "C:EIGHTH", "C:SEVENTH", "C:SIXTH", "E:SIXTH", "E:SEVENTH", "E:EIGHTH", "D:SIXTH", "D:FIFTH"
        }, delimiter = ':')
        void move_givenInvalidDirectionAndAllyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A8, targetPosition);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "검은색 폰은 목적지가 적군일 때 대각선을 제외한 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 대각선을 제외한 이동 방향, 적군 테스트")
        @CsvSource(value = {
                "D:EIGHTH", "C:EIGHTH", "C:SEVENTH", "E:SEVENTH", "E:EIGHTH", "D:SIXTH", "D:FIFTH"
        }, delimiter = ':')
        void move_givenInvalidDirectionAndEnemyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A1, targetPosition);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "검은색 폰은 목적지가 적군일 때 대각선 방향 ({0}, {1})로 이동할 수 있다.")
        @DisplayName("move() 유효한 공격 방향, 적군 테스트")
        @CsvSource(value = {"C:SIXTH", "E:SIXTH"}, delimiter = ':')
        void move_givenValidDirectionAndEnemyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A1, targetPosition);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.BLACK)).doesNotThrowAnyException();
        }


        @ParameterizedTest(name = "검은색 폰은 목적지가 빈 칸일 때 ({0} / {1}) 방향으로 이동할 수 있다.")
        @DisplayName("move() 전진 테스트")
        @CsvSource(value = {"D:SIXTH", "D:FIFTH"}, delimiter = ':')
        void move_givenValidAllAndEmptyTarget_thenSuccess(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.BLACK)).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("move() 흰색 폰 테스트")
    class WhitePawnTest {

        private final Position source = D2;
        private ChessBoard chessBoard;

        @BeforeEach
        void beforeEach() {
            chessBoard = ChessBoardFactory.create();
        }

        @ParameterizedTest(name = "흰색 폰은 유효하지 않은 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "C:THIRD", "C:SECOND", "C:FIRST", "D:FIRST", "E:FIRST", "E:SECOND", "E:THIRD"
        }, delimiter = ':')
        void move_givenInvalidDirectionAndEmptyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "흰색 폰은 목적지가 아군일 때 모든 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 모든 이동 방향, 아군 테스트")
        @CsvSource(value = {
                "C:THIRD", "C:SECOND", "C:FIRST", "D:FIRST", "E:FIRST", "E:SECOND", "E:THIRD", "D:THIRD", "D:FOURTH"
        }, delimiter = ':')
        void move_givenInvalidDirectionAndAllyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A1, targetPosition);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "흰색 폰은 목적지가 적군일 때 유효하지 않은 이동 방향 ({0}, {1})로 이동할 수 없다.")
        @DisplayName("move() 유효하지 않은 이동 방향, 적군 테스트")
        @CsvSource(value = {
                "C:SECOND", "C:FIRST", "D:FIRST", "E:FIRST", "E:SECOND", "D:THIRD", "D:FOURTH"
        }, delimiter = ':')
        void move_givenInvalidDirectionAndEnemyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A8, targetPosition);

            // when, then
            assertThatThrownBy(() -> chessBoard.move(source, targetPosition, Camp.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }

        @ParameterizedTest(name = "흰색 폰은 목적지가 적군일 때 대각선 방향 ({0}, {1})로 이동할 수 있다.")
        @DisplayName("move() 유효한 공격 방향, 적군 테스트")
        @CsvSource(value = {"C:THIRD", "E:THIRD"}, delimiter = ':')
        void move_givenValidDirectionAndEnemyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A8, targetPosition);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.WHITE)).doesNotThrowAnyException();
        }

        @ParameterizedTest(name = "흰색 폰은 목적지가 빈 칸일 때 ({0} / {1}) 방향으로 이동할 수 있다.")
        @DisplayName("move() 전진 테스트")
        @CsvSource(value = {"D:THIRD", "D:FOURTH"}, delimiter = ':')
        void move_givenValidAllAndEmptyTarget_thenSuccess(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.WHITE)).doesNotThrowAnyException();
        }
    }
}
