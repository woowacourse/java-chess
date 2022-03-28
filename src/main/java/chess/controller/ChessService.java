package chess.controller;

import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.Color;
import chess.model.Square;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private Board board;

    public Board initBoard() {
        board = new Board(new ChessInitializer());
        return board;
    }

    public Board move(String from, String to) {
        board.move(Square.of(from), Square.of(to));
        return board;
    }

    public boolean checkKingsAlive() {
        return board.aliveTwoKings();
    }

    public Board getBoard() {
        return board;
    }

    public Map<String, Double> getScores() {
        return Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Color::name, color -> board.calculatePoint(color)));
    }
}
