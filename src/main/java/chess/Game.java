package chess;

import chess.domain.*;
import com.google.gson.JsonObject;

public class Game {
    private Round round;
    private Board board;
    StatusBoard statusBoard;

    public Game() {
        round = new Round(0);
        this.board = BoardFactory.create();
    }

    public JsonObject play(int from, int to) {
        Spot startSpot = Spot.valueOf(from);
        Spot endSpot = Spot.valueOf(to);

        Board movedBoard = board.move(startSpot, endSpot);
        if (!movedBoard.equals(board)) {
            round.nextRound();
            board = movedBoard;
        }

        return new BoardJson(board).getBoardJson();
    }

    public String getScore() {
        statusBoard = StatusBoardFactory.create(board);
    }
}
