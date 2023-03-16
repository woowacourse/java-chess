package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.state.command.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.state.command.Command.parse;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Running 은")
class RunningTest {

    @Test
    void 실행_가능하다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());
        final ChessState running = initialize.command(Command.parse(List.of("start")));

        // when & then
        assertThat(running.runnable()).isTrue();
    }

    @Test
    void start_명렁어를_받으면_예외() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());
        final ChessState running = initialize.command(Command.parse(List.of("start")));

        // when & then
        assertThatThrownBy(() -> running.command(parse(of("start"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void end_명령어를_받으면_End_상태로_바뀐다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());
        final ChessState running = initialize.command(Command.parse(List.of("start")));

        // when
        final ChessState end = running.command(Command.parse(List.of("end")));

        // then
        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void 기물을_움직이면_새로_생성되는_Running_은_Turn_이_반대이다() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();
        final Initialize initialize = new Initialize(chessBoard);
        final ChessState running = initialize.command(Command.parse(List.of("start")));

        // when
        final ChessState next = running.command(parse(of("move", "b2", "b3")));

        // then
        assertThatThrownBy(() -> next.command(parse(of("move", "b3", "b4"))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
