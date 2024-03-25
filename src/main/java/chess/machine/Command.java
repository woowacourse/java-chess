package chess.machine;

import chess.domain.chessBoard.ChessBoard;
import chess.view.OutputView;

public interface Command {

    void conductCommand(ChessBoard chessBoard, OutputView outputView);
}
