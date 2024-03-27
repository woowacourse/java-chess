package chess.view;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

class ViewDataConverter {

    public CharSequence convertToViewData(Pieces pieces) {
        StringBuilder viewData = new StringBuilder();

        for (int rankValue = 8; rankValue >= 1; rankValue--) {
            viewData.append(createRowViewData(pieces, rankValue));
            viewData.append(System.lineSeparator());
        }

        return viewData;
    }

    private String createRowViewData(Pieces pieces, int rankValue) {
        return IntStream.rangeClosed('a', 'h')
                .mapToObj(operand -> new Coordinate(rankValue, (char) operand))
                .map(pieces::findByCoordinate)
                .map(this::convertToViewData)
                .collect(Collectors.joining());
    }

    private String convertToViewData(Piece piece) {
        String shape = PieceShapeSelector.selectShape(piece.getType());
        if (piece.isSameTeam(Team.BLACK)) {
            return shape.toUpperCase();
        }

        return shape.toLowerCase();
    }
}
