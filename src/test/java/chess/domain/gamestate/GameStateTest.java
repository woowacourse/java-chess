package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.Turn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Ready 상태의 명령어 입력시 상태 변화")
    void ready() {
        GameState gameState = new Ready(board);

        assertThatIllegalArgumentException().isThrownBy(gameState::end);
        assertThatIllegalArgumentException().isThrownBy(gameState::status);
        assertThatIllegalArgumentException().isThrownBy(gameState::winner);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> gameState.move(Point.of("a1"), Point.of("b1"), new Turn(Team.WHITE)));
        assertThat(gameState.start()).hasSameClassAs(new Running(board));
    }

    @Test
    @DisplayName("Running 상태의 명령어 입력시 상태 변화")
    void running() {
        board.putSymmetrically(Piece.KING, Point.of("a1"));
        GameState gameState = new Running(board);

        assertThatIllegalArgumentException().isThrownBy(gameState::winner);
        assertThat(gameState.end()).hasSameClassAs(new Finished(board, Team.NONE));
        assertThat(gameState.status()).hasSameClassAs(new Running(board));
        assertThat(gameState.move(Point.of("a1"), Point.of("b1"), new Turn(Team.WHITE)))
            .hasSameClassAs(new Running(board));
        assertThat(gameState.start()).hasSameClassAs(new Running(board));
    }

    @Test
    @DisplayName("Running 상태에서 KING 이 잡히면 게임 종료")
    void gameIsOverWhenKingIsDead() {
        board.putSymmetrically(Piece.KING, Point.of("c4"));
        GameState gameState = new Running(board);

        assertThat(gameState.move(Point.of("c4"), Point.of("c5"), new Turn(Team.WHITE)))
            .hasSameClassAs(new Finished(board, Team.WHITE));
    }

    @Test
    @DisplayName("Finished 상태의 명령어 입력시 상태 변화")
    void finished() {
        GameState gameState = new Finished(board, Team.WHITE);

        assertThat(gameState.start()).hasSameClassAs(new Running(board));
        assertThatIllegalArgumentException().isThrownBy(gameState::end);
        assertThatIllegalArgumentException().isThrownBy(gameState::status);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> gameState.move(Point.of("a1"), Point.of("b1"), new Turn(Team.WHITE)));
        assertThat(gameState.winner()).isEqualTo(Team.WHITE);
    }
}