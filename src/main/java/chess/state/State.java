package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.Position;
import chess.chessgame.Turn;
import chess.piece.Color;

public interface State {

    State start();

    State move(Position position, Turn turn);

    State end();

    Chessboard getChessboard();

    boolean isFinished();

    double computeScore(Color color);
}
