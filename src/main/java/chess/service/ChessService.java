package chess.service;

import chess.model.Color;
import chess.model.Square;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.util.PieceToLetterConvertor;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private Board board;

    public List<String> initBoard() {
        board = new Board(new ChessInitializer());
        return toDto(board);
    }

    private List<String> toDto(final Board board) {
        return board.getBoard().stream()
                .map(PieceToLetterConvertor::convertToLetter)
                .collect(Collectors.toList());
    }

    public List<String> move(String from, String to) {
        board.move(Square.of(from), Square.of(to));
        return toDto(board);
    }

    public boolean checkKingsAlive() {
        return board.aliveTwoKings();
    }

    public Map<String, Double> getScores() {
        return Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Color::name, color -> board.calculatePoint(color)));
    }
}
