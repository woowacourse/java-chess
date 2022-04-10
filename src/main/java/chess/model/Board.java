package chess.model;

import chess.Member;
import chess.model.status.Status;
import java.util.ArrayList;
import java.util.List;

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
