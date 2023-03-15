package chess.controller;

import chess.model.Color;
import chess.model.Type;
import chess.model.board.Square;
import chess.view.PieceMessageConverter;
import java.util.List;
import java.util.stream.Collectors;

public class BoardResponse {

    private final List<String> squares;

    public BoardResponse(final List<Square> squares) {
        this.squares = rendBoardMessage(squares);
    }

    private List<String> rendBoardMessage(final List<Square> squares) {
        return squares.stream()
                .map(square -> rend(square.getType(), square.getColor()))
                .collect(Collectors.toList());
    }

    private String rend(final Type type, final Color color) {
        return PieceMessageConverter.convert(type, color);
    }

    public List<String> getSquares() {
        return squares;
    }
}
