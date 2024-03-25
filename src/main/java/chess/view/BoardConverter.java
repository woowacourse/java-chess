package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class BoardConverter {

    List<List<String>> convertToViewData(Board board) {
        List<List<String>> viewData = new ArrayList<>();

        for (int rankValue = 8; rankValue >= 1; rankValue--) {
            viewData.add(createRowData(board, rankValue));
        }

        return viewData;
    }

    private List<String> createRowData(Board board, int rankValue) {
        return IntStream.rangeClosed('a', 'h')
                .mapToObj(operand -> Coordinate.of(File.from(operand), Rank.from(rankValue)))
                .map(board::findByCoordinate)
                .map(this::convert)
                .toList();
    }

    private String convert(Piece piece) {
        String shape = PieceShape.valueOf(piece.getType()).getShape();
        if (piece.getTeam() == Team.BLACK) {
            return shape.toUpperCase();
        }

        return shape.toLowerCase();
    }
}
