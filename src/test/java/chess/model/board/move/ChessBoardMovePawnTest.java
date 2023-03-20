package chess.model.board.move;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A8;
import static chess.helper.PositionFixture.D2;
import static chess.helper.PositionFixture.D4;
import static chess.helper.PositionFixture.D5;
import static chess.helper.PositionFixture.D6;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardMovePawnTest {

    @Nested
    @DisplayName("move() 검은색 폰 테스트")
    class BlackPawnTest {

        private final Position source = D5;
        private ChessBoard chessBoard;

        @BeforeEach
        void beforeEach() {
            chessBoard = ChessBoardFactory.create();
            ChessBoardPieceMovingHelper.move(chessBoard, D7, source);
        }

        @ParameterizedTest(name = "검은색 폰은 유효하지 않은 이동 방향 ({0}, {1})으로 이동할 수 없다.")
        @DisplayName("move() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "D:SIXTH", "C:SIXTH", "C:FIFTH", "C:FOURTH", "E:FOURTH", "E:FIFTH", "E:SIXTH"
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
                "D:SIXTH", "C:SIXTH", "C:FIFTH", "C:FOURTH", "D:FOURTH", "E:FOURTH", "E:FIFTH", "E:SIXTH"
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
        @CsvSource(value = {"D:SIXTH", "C:SIXTH", "C:FIFTH", "D:FOURTH", "E:FIFTH", "E:SIXTH"}, delimiter = ':')
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
        @CsvSource(value = {"C:FOURTH", "E:FOURTH"}, delimiter = ':')
        void move_givenValidDirectionAndEnemyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A1, targetPosition);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.BLACK)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("검은색 폰은 목적지가 빈 칸일 때 (0, -1) 방향으로 이동할 수 있다.")
        void move_givenValidAllAndEmptyTarget_thenSuccess() {
            // when, then
            assertThatCode(() -> chessBoard.move(source, D4, Camp.BLACK)).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("move() 흰색 폰 테스트")
    class WhitePawnTest {

        private final Position source = D5;
        private ChessBoard chessBoard;

        @BeforeEach
        void beforeEach() {
            chessBoard = ChessBoardFactory.create();
            ChessBoardPieceMovingHelper.move(chessBoard, D2, source);
        }

        @ParameterizedTest(name = "흰색 폰은 유효하지 않은 이동 방향 ({0}, {1})으로 이동할 수 없다.")
        @DisplayName("move() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "C:SIXTH", "C:FIFTH", "C:FOURTH", "D:FOURTH", "E:FOURTH", "E:FIFTH", "E:SIXTH"
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
                "D:SIXTH", "C:SIXTH", "C:FIFTH", "C:FOURTH", "D:FOURTH", "E:FOURTH", "E:FIFTH", "E:SIXTH"
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
        @CsvSource(value = {"D:SIXTH", "C:FIFTH", "C:FOURTH", "D:FOURTH", "E:FOURTH", "E:FIFTH"}, delimiter = ':')
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
        @CsvSource(value = {"C:SIXTH", "E:SIXTH"}, delimiter = ':')
        void move_givenValidDirectionAndEnemyTarget_thenFail(final File targetFile, final Rank targetRank) {
            // given
            final Position targetPosition = Position.of(targetFile, targetRank);
            ChessBoardPieceMovingHelper.move(chessBoard, A8, targetPosition);

            // when, then
            assertThatCode(() -> chessBoard.move(source, targetPosition, Camp.WHITE)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("흰색 폰은 목적지가 빈 칸일 때 (0, 1) 방향으로 이동할 수 있다.")
        void move_givenValidAllAndEmptyTarget_thenSuccess() {
            // when, then
            assertThatCode(() -> chessBoard.move(source, D6, Camp.WHITE)).doesNotThrowAnyException();
        }
    }
}
