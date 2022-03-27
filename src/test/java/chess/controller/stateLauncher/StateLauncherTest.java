package chess.controller.stateLauncher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.Fixtures;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.stateLauncher.KingGo;
import chess.domain.game.stateLauncher.Play;
import chess.domain.game.stateLauncher.StateLauncher;
import chess.domain.game.stateLauncher.Status;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import chess.view.OutputView;
import java.util.Map;
import org.junit.jupiter.api.Test;

class StateLauncherTest {
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
        StateLauncher stateLauncher = new Play(new ChessGame(new Board(stringPieceMap)));
        stateLauncher = stateLauncher.go("move h1 h4");

        assertThat(stateLauncher)
                .isInstanceOf(KingGo.class);
        stateLauncher = stateLauncher.go("status");
        assertThat(stateLauncher)
                .isInstanceOf(Status.class);

        assertThat(chessGame.getStatus().getWinner())
                .isEqualTo(Team.WHITE);
    }
}
