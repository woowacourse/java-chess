package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

class ViewDataConverter {

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
                .map(this::convertToViewData)
                .toList();
    }

    private String convertToViewData(Piece piece) {
        String shape = PieceShapeSelector.selectShape(piece.getType());
        if (piece.getTeam() == Team.BLACK) {
            return shape.toUpperCase();
        }

        return shape.toLowerCase();
    }
}
