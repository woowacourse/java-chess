package chess.domain.state;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static chess.PositionFixture.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ChessEndTest {

    private static final Pawn pawn = new Pawn(A2, Color.WHITE);

    @ParameterizedTest
    @MethodSource("provideCommand")
    @DisplayName("게임 커멘드 입력시 체스가 종료되었다는 예외를 발생시킨다.")
    void command_process_throws_exception(final Command command) {
        final ChessEnd chessEnd = new ChessEnd(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

        assertThatThrownBy(() -> chessEnd.process(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.GAME_END.name());
    }

    private static Stream<Arguments> provideCommand() {
        return Stream.of(
                Arguments.of(Command.of(List.of("start"))),
                Arguments.of(Command.of(List.of("move", "a2", "a3"))),
                Arguments.of(Command.of(List.of("end")))
        );
    }

    @Test
    @DisplayName("체스말을 가져온다")
    void getting_existing_piece_test() {
        final ChessEnd chessEnd = new ChessEnd(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

        final Set<Piece> existingPieces = chessEnd.getExistingPieces();

        assertSoftly(softly -> {
            softly.assertThat(existingPieces).hasSize(1);
            softly.assertThat(existingPieces).containsExactly(pawn);
        });

    }

    @Test
    @DisplayName("게임 종료 여부를 확인한다")
    void check_if_is_end_test() {
        final ChessEnd chessEnd = new ChessEnd(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

        final boolean isEnd = chessEnd.isEnd();

        assertThat(isEnd).isTrue();
    }

}
