package chess;

import chess.piece.Color;
import chess.state.Ready;
import chess.state.State;
import chess.utils.PositionParser;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void move(String command) {
        List<String> moveInfo = List.of(command.split(" "));
        String[] source = moveInfo.get(1).split("");
        String[] target = moveInfo.get(2).split("");

        Pair<Integer, Integer> parsedSource = PositionParser.parse(source[0], source[1]);
        Pair<Integer, Integer> parsedTarget = PositionParser.parse(target[0], target[1]);
        state = state.move(parsedSource, parsedTarget);
    }

    public void end() {
        state = state.end();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double computeScore(Color color){
        return state.computeScore(color);
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }
}
