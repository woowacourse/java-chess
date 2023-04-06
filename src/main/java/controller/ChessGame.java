package controller;

import domain.Board;

public class ChessGame {
    private final String id;
    private final Board board;

    public ChessGame(String id, Board board) {
        this.id = id;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public Board getChessGame() {
        return board;
    }
}
