package domain;

import domain.piece.*;
import domain.util.BoardInitializer;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Piece>> pieceStatus;

    public Board(List<List<Piece>> pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    public static Board initialize() {
        return new Board(BoardInitializer.initializeBoard());
    }

    public List<List<Piece>> findCurrentStatus() {
        return new ArrayList<>(pieceStatus);
    }
}
