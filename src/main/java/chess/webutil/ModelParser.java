package chess.webutil;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.sql.SQLException;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class ModelParser {
    private static final String BLANK = "blank";
    private static final String MOVABLE = "movable";
    private static final String MARK = "_mark";

    public static Map<String, Object> parseBlankBoard() {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(Position::toString, position -> BLANK));
    }

    public static Map<String, Object> parseBoard(Board board) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        output.putAll(parseMovablePlaces(new ArrayList<>()));

        return output;
    }

    public static Map<String, Object> parseBoard(Board board, List<Position> movablePlaces) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        output.putAll(parseMovablePlaces(movablePlaces));

        return output;
    }

    private static String parsePiece(Optional<Piece> piece) {
        if (piece.isPresent()) {
            return piece.get().toString();
        }
        return BLANK;
    }

    public static Map<String, Object> parseMovablePlaces(List<Position> markingPlaces) {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(position -> parseMarkPosition(position), position -> parseMovable(position, markingPlaces)));
    }

    private static String parseMarkPosition(Position position) {
        return position.toString() + MARK;
    }

    private static String parseMovable(Position position, List<Position> positions) {
        if (positions.contains(position)) {
            return MOVABLE;
        }
        return BLANK;
    }
}
