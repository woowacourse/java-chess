package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board() {
            @Override
            public boolean canMove(Point source, Point destination, Team currentTeam) {
                return true;
            }
        };
    }

    @Test
    @DisplayName("Ready 상태의 명령어 입력시 상태 변화")
    void ready() {
        GameState gameState = new Ready(board);

        assertThatIllegalArgumentException().isThrownBy(gameState::end);
        assertThatIllegalArgumentException().isThrownBy(gameState::status);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> gameState.move(Point.of("a1"), Point.of("b1"), new Turn(Team.WHITE)));
        assertThat(gameState.start()).hasSameClassAs(new Running(board));
    }

    @Test
    @DisplayName("Running 상태의 명령어 입력시 상태 변화")
    void running() {
        GameState gameState = new Running(board);

        assertThatIllegalArgumentException().isThrownBy(gameState::start);
        assertThat(gameState.end()).hasSameClassAs(new Finished());
        assertThat(gameState.status()).hasSameClassAs(new Running(board));
        assertThat(gameState.move(Point.of("a1"), Point.of("b1"), new Turn(Team.WHITE)))
            .hasSameClassAs(new Running(board));
    }

    @Test
    @DisplayName("Finished 상태의 명령어 입력시 상태 변화")
    void finished() {
        GameState gameState = new Finished();

        assertThatIllegalArgumentException().isThrownBy(gameState::start);
        assertThatIllegalArgumentException().isThrownBy(gameState::end);
        assertThatIllegalArgumentException().isThrownBy(gameState::status);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> gameState.move(Point.of("a1"), Point.of("b1"), new Turn(Team.WHITE)));
    }
}