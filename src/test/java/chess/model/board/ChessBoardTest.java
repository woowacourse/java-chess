package chess.model.board;

import static chess.helper.PositionFixture.A6;
import static chess.helper.PositionFixture.A7;
import static chess.helper.PositionFixture.E1;
import static chess.helper.PositionFixture.E7;
import static chess.helper.PositionFixture.E8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.helper.ChessBoardPieceMovingHelper;
import chess.model.piece.Camp;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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
}
