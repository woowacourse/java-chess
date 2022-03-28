package chess.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.PieceRepresentation;

public class BoardResponse implements Response {

    private final List<List<String>> information;
    private final String metaInformation;

    public BoardResponse(List<List<String>> information, String metaInformation) {
        this.information = information;
        this.metaInformation = metaInformation;
    }

    public static Response of(Map<Point, Piece> pointPieces, Color turnColor) {
        List<List<String>> information = new ArrayList<>();

        for (int i = LineNumber.MAX; i >= LineNumber.MIN; i--) {
            information.add(ofLine(pointPieces, i));
        }
        return new BoardResponse(information, turnColor.name());
    }

    private static List<String> ofLine(Map<Point, Piece> pointPieces, int i) {
        List<String> line = new ArrayList<>();

        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            Piece piece = pointPieces.get(Point.of(j, i));
            line.add(PieceRepresentation.convertType(piece));
        }
        return line;
    }

    @Override
    public String getInformation() {
        StringBuilder builder = new StringBuilder();
        for (List<String> line : information) {
            appendLine(builder, line);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private void appendLine(StringBuilder builder, List<String> line) {
        for (String s : line) {
            builder.append(s);
        }
    }

    @Override
    public String getMetaInformation() {
        return "현재 턴 : " + metaInformation;
    }
}
