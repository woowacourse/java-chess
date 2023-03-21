package chess.domain.state;

import chess.TestPiecesGenerator;
import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.state.ChessEnd.GAME_END_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ChessEndTest {

    private static final Pawn pawn = new Pawn(Position.of(File.A, Rank.TWO), Color.WHITE);

    @ParameterizedTest
    @MethodSource("provideCommand")
    @DisplayName("게임 커멘드 입력시 체스가 종료되었다는 예외를 발생시킨다.")
    void command_process_throws_exception(final Command command) {
        final ChessEnd chessEnd = new ChessEnd(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

        assertThatThrownBy(() -> chessEnd.process(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(GAME_END_MESSAGE);
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

        final List<Piece> existingPieces = chessEnd.getExistingPieces();
        final Piece piece = existingPieces.get(0);

        assertSoftly(softly -> {
            softly.assertThat(piece).isInstanceOf(Pawn.class);
            softly.assertThat(piece.getPosition()).isEqualTo(Position.of(File.A, Rank.TWO));
            softly.assertThat(piece.getColor()).isEqualTo(Color.WHITE);
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
