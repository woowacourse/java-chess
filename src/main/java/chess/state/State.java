package chess.state;

import chess.Chessboard;
import org.apache.commons.lang3.tuple.Pair;

public interface State {

    State start();

    State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target);

    Chessboard getChessboard();
}
