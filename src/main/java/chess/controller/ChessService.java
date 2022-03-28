package chess.controller;

import chess.model.Board;
import chess.model.Color;
import chess.model.Square;

public final class ChessService {
    private Board board;

    public void initBoard() {
        board = new Board();
    }

    public Board move(final String from, final String to) {
        board.move(Square.of(from), Square.of(to));
        return board;
    }

    public boolean checkKingsAlive() {
        return board.aliveTwoKings();
    }

    public Board getBoard() {
        return board;
    }

    public ScoreResult getScores() {
        final ScoreResult scoreResult = new ScoreResult();
        for (Color color : Color.getPlayerColors()) {
            scoreResult.addResult(color, board.calculatePoint(color));
        }
        return scoreResult;
    }
}

