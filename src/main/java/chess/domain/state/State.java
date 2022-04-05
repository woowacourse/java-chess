package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;

public interface State {

    State proceed(final ChessBoard chessBoard, final GameCommand gameCommand);

    boolean isFinished();
}
