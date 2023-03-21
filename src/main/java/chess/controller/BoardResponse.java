package chess.controller;

import static java.util.stream.Collectors.toList;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Positions;
import chess.model.position.Rank;
import chess.view.PieceMessageConverter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoardResponse {

    private final List<String> squares;

    public BoardResponse(final Map<Position, Piece> squares) {
        this.squares = rendBoardMessage(squares);
    }

    private List<String> rendBoardMessage(final Map<Position, Piece> squares) {
        final List<Position> allPositions = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> Positions.getInstance(file, rank)))
                .collect(toList());

        return allPositions.stream()
                .map(squares::get)
                .map(piece -> rend(piece.getType(), piece.getColor()))
                .collect(toList());
    }

    private String rend(final Type type, final Color color) {
        return PieceMessageConverter.convert(type, color);
    }

    public List<String> getSquares() {
        return squares;
    }
}
