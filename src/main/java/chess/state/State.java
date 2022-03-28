package chess.state;

import chess.Chessboard;
import chess.piece.Color;
import chess.position.Position;

public interface State {

    State start();

    State move(Position source, Position target);

    State end();

    boolean isFinished();

    double computeScore(Color color);

    Chessboard getChessboard();
}
