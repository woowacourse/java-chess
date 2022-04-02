package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.Color;

public interface State {

    State proceed(final ChessBoard chessBoard, final GameCommand gameCommand);

    boolean isFinished();

    Color getWinner();
}
