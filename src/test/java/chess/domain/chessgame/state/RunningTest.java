package chess.domain.chessgame.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessFactory;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RunningTest {

    private static Running running;

    @BeforeEach
    void setUp() {
        running = new Running(new ChessBoard(ChessFactory.create()), Team.WHITE);
    }

    @Test
    void 게임중_상태일떄_실행시_예외발생_테스트() {
        assertThatThrownBy(running::start).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임중_상태일때_종료_입력시_종료상태를_반환한다() {
        assertThat(running.end()).isInstanceOf(End.class);
    }

    @Test
    void 게임중_상태일때_종료되지_않았음을_반환한다() {
        assertThat(running.isNotEnd()).isTrue();
    }

}