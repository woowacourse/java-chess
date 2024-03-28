package chess.machine;

import chess.domain.chessGame.ChessGame;
import chess.view.OutputView;

public interface Command {

    void conductCommand(ChessGame chessGame, OutputView outputView);
}
