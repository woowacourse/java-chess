package chess.domain.game;

import chess.domain.pieces.Color;

public class NeoBoard {

    private int id;
    private final String roomTitle;
    private Color turn;

    public NeoBoard(int id, String roomTitle, Color turn) {
        this.id = id;
        this.roomTitle = roomTitle;
        this.turn = turn;
    }

    public NeoBoard(int id, String roomTitle) {
        this(id, roomTitle, Color.WHITE);
    }

    public NeoBoard(String roomTitle) {
        this(0, roomTitle);
    }

    public int getId() {
        return id;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public Color getTurn() {
        return turn;
    }
}
