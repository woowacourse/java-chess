package chess.state;

import chess.Chessboard;
import chess.piece.Color;
import org.apache.commons.lang3.tuple.Pair;

public interface State {

    State start();

    State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target);

    State end();

    boolean isFinished();

    double computeScore(Color color);

    Chessboard getChessboard();
}
