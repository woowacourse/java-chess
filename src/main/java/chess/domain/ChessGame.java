package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.utils.PositionParser;

import java.util.List;

public class ChessGame {

    private static final String COMMAND_DELIMITER = " ";
    private static final String LOCATION_DELIMITER = "";
    private static final int ROW = 0;
    private static final int COLUMN = 1;
    private static final int SOURCE = 1;
    private static final int TARGET = 2;

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void move(String command) {
        List<String> moveInfo = List.of(command.split(COMMAND_DELIMITER));
        String[] source = moveInfo.get(SOURCE).split(LOCATION_DELIMITER);
        String[] target = moveInfo.get(TARGET).split(LOCATION_DELIMITER);

        Position parsedSource = PositionParser.parse(source[ROW], source[COLUMN]);
        Position parsedTarget = PositionParser.parse(target[ROW], target[COLUMN]);
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
