package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.Color;

public interface State {

    State proceed(ChessBoard chessBoard, GameCommand gameCommand);

    boolean isFinished();

    Color getWinner();
}
