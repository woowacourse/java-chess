package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.List;

public class ChessGame {

    private static final String COMMAND_DELIMITER = " ";
    private static final String LOCATION_DELIMITER = "";
    private static final int ROW = 1;
    private static final int COLUMN = 0;
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

        state = state.move(Position.of(source[ROW], source[COLUMN]), Position.of(target[ROW], target[COLUMN]));
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
