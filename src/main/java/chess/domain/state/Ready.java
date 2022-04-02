package chess.domain.state;

import chess.domain.Turn;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class Ready implements State {

    private final Map<Position, Piece> chessboard;
    private final Turn turn;

    public Ready() {
        this.chessboard = new LinkedHashMap<>();
        this.turn = new Turn();
    }

    @Override
    public State start() {
        return Play.from(turn);
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        return new Finish(new Chessboard(chessboard));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double computeScore(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Chessboard getChessboard() {
        return new Chessboard(chessboard);
    }
}
