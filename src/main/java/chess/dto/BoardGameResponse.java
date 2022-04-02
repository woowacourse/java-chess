package chess.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Piece;
import chess.view.PieceRepresentation;

public class BoardGameResponse extends GameResponse {

    public static final int DECIMAL = 10;

    private BoardGameResponse(Map<String, String> information, String metaInformation) {
        super(information, metaInformation);
    }

    public static GameResponse of(Map<Point, Piece> pointPieces, Color turnColor) {
        return new BoardGameResponse(toInformation(pointPieces), turnColor.name());
    }

    private static Map<String, String> toInformation(Map<Point, Piece> pointPieces) {
        Map<String, String> information = new HashMap<>();
        for (int verticalIndex = LineNumber.MIN; verticalIndex <= LineNumber.MAX; verticalIndex++) {
            information.putAll(toLine(pointPieces, verticalIndex));
        }
        return information;
    }

    private static Map<String, String> toLine(Map<Point, Piece> pointPieces, int verticalIndex) {
        Map<String, String> lineInformation = new HashMap<>();
        for (int horizontalIndex = LineNumber.MIN; horizontalIndex <= LineNumber.MAX; horizontalIndex++) {
            lineInformation.put(toKey(verticalIndex, horizontalIndex),
                PieceRepresentation.convertType(pointPieces.get(Point.of(horizontalIndex, verticalIndex))));
        }
        return lineInformation;
    }

    private static String toKey(int verticalIndex, int horizontalIndex) {
        return String.valueOf(verticalIndex * DECIMAL + horizontalIndex);
    }
}
