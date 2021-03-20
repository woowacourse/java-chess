package chess.service;

import chess.domain.grid.Grid;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessService {
    private final Grid grid;

    public ChessService() {
        grid = new Grid();
    }

    public Grid grid() {
        return grid;
    }

    public double score(boolean isBlack) {
        return grid.score(isBlack);
    }

    public Piece piece(Position position) {
        return grid.piece(position);
    }

    public void move(Piece source, Piece target) {
        grid.move(source, target);
    }
}
