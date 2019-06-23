package chess.utils;

import java.util.HashMap;
import java.util.Map;

import chess.domain.AbstractPiece;
import chess.domain.Board;
import chess.domain.Coordinate;
import chess.domain.Count;
import chess.domain.Position;
import chess.domain.Team;

public class DataParser {
    public static AbstractPiece piece(String name) {
        return PieceParser.parse(name);
    }

    public static Map<String, String> board(Board board) {
        Map<String, String> result = new HashMap<>();
        board.getBoard()
                .forEach((key, value) -> result.put(WebUtils.positionParser(key), toPieceName(value)));
        return result;
    }

    private static String toPieceName(AbstractPiece value) {
        String resultValue = value.getName();
        if (value.getTeam() == Team.BLACK) {
            resultValue = resultValue.toUpperCase();
        }
        return resultValue;
    }

    public static Position position(String x, String y) {
        return new Position(new Coordinate(x.charAt(0)), new Coordinate(Integer.parseInt(y)));
    }

    public static Count count(int count) {
        return new Count(count);
    }

}
