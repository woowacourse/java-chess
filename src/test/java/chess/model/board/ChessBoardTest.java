package chess.model.board;

import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A6;
import static chess.helper.PositionFixture.A7;
import static chess.helper.PositionFixture.B3;
import static chess.helper.PositionFixture.B4;
import static chess.helper.PositionFixture.C2;
import static chess.helper.PositionFixture.E1;
import static chess.helper.PositionFixture.E7;
import static chess.helper.PositionFixture.E8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.helper.ChessBoardPieceMovingHelper;
import chess.model.piece.Camp;
import chess.model.piece.PieceScore;
import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    private final ChessBoard chessBoard = ChessBoardFactory.create();
    
    @Test
    @DisplayName("move()에서 이동시키고자 하는 기물이 자신의 기물이 아니라면 예외가 발생한다.")
    void move_givenNotAllyPiece_thenFail() {
        // when, then
        assertThatThrownBy(() -> chessBoard.move(A7, A6, Camp.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 진행하는 플레이어의 기물이 아닙니다.");
    }

    @ParameterizedTest(name = "Camp.WHITE일 때 기물이 ({0}, {1})로 이동하면 {2}를 반환한다.")
    @DisplayName("canPlayGame() 흰색 진영 테스트")
    @MethodSource("provideCanPlayGameArguments")
    void canPlayGame_givenWhiteCamp_thenReturnGameOnGoing(final Position position, final boolean expected) {
        // given
        ChessBoardPieceMovingHelper.move(chessBoard, E7, position);

        // when
        final boolean actual = chessBoard.canPlayGame(Camp.WHITE);

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideCanPlayGameArguments() {
        return Stream.of(
                Arguments.of(E8, true), Arguments.of(E1, false)
        );
    }

    @Test
    @DisplayName("calculateScoreByCamp()는 진영을 건네주면 해당 진영의 기물 점수를 계산해 반환한다")
    void calculateScoreByCamp_givenCamp_thenReturnTotalPieceScore() {
        final PieceScore initialPieceScore = chessBoard.calculateScoreByCamp(Camp.WHITE);
        assertThat(initialPieceScore.getValue()).isEqualTo(38.0d);

        ChessBoardPieceMovingHelper.move(chessBoard, A2, B3);
        final PieceScore doublePawnPieceScore = chessBoard.calculateScoreByCamp(Camp.WHITE);
        assertThat(doublePawnPieceScore.getValue()).isEqualTo(37.0d);

        ChessBoardPieceMovingHelper.move(chessBoard, C2, B4);
        final PieceScore triplePawnPieceScore = chessBoard.calculateScoreByCamp(Camp.WHITE);
        assertThat(triplePawnPieceScore.getValue()).isEqualTo(36.5d);
    }
}
