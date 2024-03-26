package chess.machine;

import chess.domain.chessBoard.ChessGame;
import chess.view.OutputView;

public interface Command {

    void conductCommand(ChessGame chessGame, OutputView outputView);
}
