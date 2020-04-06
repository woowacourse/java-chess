package chess.view;

import chess.Board;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WebOutputView {
    public static String printBoard(Board board) {
        System.out.println("printBoard들어옴!");
        board.getPieces().keySet()
                .forEach(position -> System.out.println(String.format("%s : %s", position, board.getPieces().get(position))));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for (Rank rank : reverseOrderOfRankValuesExceptNone()) {
            stringBuilder.append("<tr>");
            for (File file : File.valuesExceptNone()) {
                Piece piece = board.getPiece(Position.of(file, rank));
                //System.out.println(Position.of(file,rank).getKey());
                stringBuilder.append(String.format("<td class=\"%s\">", file.getName() + rank.getName()));
                if (piece != null && piece.isNotEmpty()) {
                    String imageName = piece.isBlack() ? "B" + piece.getSymbol() : "W" + piece.getSymbol();
                    stringBuilder.append(String.format("<img src=\"%s.png\">", imageName));
                }
                stringBuilder.append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    public static List<Rank> reverseOrderOfRankValuesExceptNone() {
        return Arrays.stream(Rank.valuesExceptNone())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

}
