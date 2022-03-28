package chess.controller.stateLauncher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.Fixtures;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Play;
import chess.domain.game.state.Result;
import chess.domain.game.state.State;
import chess.domain.game.state.StatusEnd;
import chess.domain.piece.attribute.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {
    private final ChessGame chessGame = new ChessGame();

    @Test
    @DisplayName("게임종료 후 상태를 확인할 수 있다.")
    void goEnd() {
        String boardString = "........ "
                + "........ "
                + "........ "
                + "........ "
                + ".......K "
                + "........ "
                + "........ "
                + "R......q";

        State state = new Play(new ChessGame(new Board(Fixtures.stringToBoard(boardString))));

        state = state.go("move a1 a2");
        assertThat(state)
                .isInstanceOf(Play.class);
        assertThat(state.isPlay())
                .isTrue();
        state = state.go("move h1 h4");

        assertThat(state)
                .isInstanceOf(Result.class);
        state = state.go("status");
        assertThat(state)
                .isInstanceOf(StatusEnd.class);
        assertThat(chessGame.getStatus().getWinner())
                .isEqualTo(Team.WHITE);
    }
}
