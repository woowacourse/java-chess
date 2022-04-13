package chess.web;

import chess.domain.ChessScore;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDTO {

    private Map<String, Element> data;

    private BoardDTO() {
    }

    public static BoardDTO buildModel() {
        return new BoardDTO();
    }

    public void update(Board board) {
        Map<String, Element> model = new HashMap<>();
        Map<Position, Piece> original = board.getPieces();
        for (Position position : original.keySet()) {
            model.put(position.getPosition(), Element.from(original.get(position)));
        }
        this.data = model;
    }

    public Map<String, Element> getData() {
        return data;
    }

    public void updateWithScore(Board board, ChessScore chessScore) {
        Map<String, Element> model = new HashMap<>();
        Map<Position, Piece> original = board.getPieces();
        for (Position position : original.keySet()) {
            model.put(position.getPosition(), Element.from(original.get(position)));
        }
        model.put("score", Element.from(chessScore));
        this.data = model;
    }

    public void updateWithMessage(Board board, String message) {
        Map<String, Element> model = new HashMap<>();
        Map<Position, Piece> original = board.getPieces();
        for (Position position : original.keySet()) {
            model.put(position.getPosition(), Element.from(original.get(position)));
        }
        model.put("exception", Element.from(message));
        this.data = model;
    }
}
