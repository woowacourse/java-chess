package chess.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Piece;
import chess.view.PieceRepresentation;

public class BoardResponse extends Response {

    private BoardResponse(Map<String, String> information, String metaInformation) {
        super(information, metaInformation);
    }

    public static Response of(Map<Point, Piece> pointPieces, Color turnColor) {
        return new BoardResponse(toInformation(pointPieces), turnColor.name());
    }

    private static Map<String, String> toInformation(Map<Point, Piece> pointPieces) {
        Map<String, String> information = new HashMap<>();
        for (int i = LineNumber.MIN; i <= LineNumber.MAX; i++) {
            information.putAll(toLine(pointPieces, i));
        }
        return information;
    }

    private static Map<String, String> toLine(Map<Point, Piece> pointPieces, int i) {
        Map<String, String> lineInformation = new HashMap<>();
        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            lineInformation.put(toKey(i, j), PieceRepresentation.convertType(pointPieces.get(Point.of(j, i))));
        }
        return lineInformation;
    }

    private static String toKey(int i, int j) {
        return String.valueOf(i * 10 + j);
    }
}
