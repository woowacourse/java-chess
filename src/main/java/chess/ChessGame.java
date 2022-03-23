package chess;

import chess.state.Finish;
import chess.state.Ready;
import chess.state.State;
import chess.utils.PositionParser;
import org.apache.commons.lang3.tuple.Pair;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void move(String source, String target) {
        Pair<Integer, Integer> parsedSource = PositionParser.parse(source.charAt(0), source.charAt(1));
        Pair<Integer, Integer> parsedTarget = PositionParser.parse(target.charAt(0), target.charAt(1));
        state = state.move(parsedSource, parsedTarget);
    }

    public void end() {
        state = new Finish(getChessBoard());
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }

    public boolean isFinished() {
        return state.isFinished();
    }
}
