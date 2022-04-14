package chess.model.board;

import chess.model.status.Status;

public class Board {

    private final int id;
    private final Status status;

    public Board(Status status) {
        this(0, status);
    }

    public Board(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
