import chess.controller.ChessController;
import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.GameStatus;
import chess.domain.game.Turn;
import chess.domain.piece.Team;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final ChessController chessController = new ChessController(new InputView(), new OutputView(),
                new ChessGame(ChessBoard.createBoard(), new Turn(List.of(Team.WHITE, Team.BLACK)), GameStatus.IDLE));
        chessController.run();
    }
}
