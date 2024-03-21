package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

class Converter {

    private static final String DEFAULT_SHAPE = ".";

    List<List<String>> convertToViewData(Board board) {
        List<List<String>> viewData = new ArrayList<>();

        for (int rankValue = 8; rankValue >= 1; rankValue--) {
            viewData.add(createRowData(board, rankValue));
        }

        return viewData;
    }

    private List<String> createRowData(Board board, int rankValue) {
        return IntStream.rangeClosed('a', 'h')
                .mapToObj(operand -> new Coordinate(rankValue, (char) operand))
                .map(board::findByCoordinate)
                .map(this::getShapeOrDefault)
                .toList();
    }

    private String getShapeOrDefault(Piece piece) {
        if (piece == null) {
            return DEFAULT_SHAPE;
        }

        return convert(piece);
    }

    private String convert(Piece piece) {
        String shape = PieceShape.valueOf(piece.getType()).getShape();
        if (piece.getTeam() == Team.BLACK) {
            return shape.toUpperCase();
        }

        return shape.toLowerCase();
    }
}
