package chess.controller.stateLauncher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.Fixtures;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Result;
import chess.domain.game.state.Play;
import chess.domain.game.state.State;
import chess.domain.game.state.StatusEnd;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import chess.view.OutputView;
import java.util.Map;
import org.junit.jupiter.api.Test;

class StateTest {
    private final ChessGame chessGame = new ChessGame();

    @Test
    void goEnd() {
        String boardString =
                "........ "
                        + "........ "
                        + "........ "
                        + "........ "
                        + ".......K "
                        + "........ "
                        + "........ "
                        + ".......q";

        Map<Position, Piece> stringPieceMap = Fixtures.stringToBoard(boardString);
        OutputView.printChessBoard(new Board(stringPieceMap));
        State state = new Play(new ChessGame(new Board(stringPieceMap)));
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
