package chess.state;

import chess.Chessboard;
import chess.Turn;
import chess.piece.Color;
import org.apache.commons.lang3.tuple.Pair;

public interface State {

    State start();

    State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Turn turn);

    State end();

    Chessboard getChessboard();

    boolean isFinished();

    double computeScore(Color color);
}
