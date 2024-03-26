package chess.view.input.command;

import chess.domain.Position;
import java.util.List;

public class PositionConverter {

    public static List<Position> convert(final List<String> positions) {
        String from = positions.get(0);
        String to = positions.get(1);
        return List.of(convertPosition(from), convertPosition(to));
    }

    private static Position convertPosition(String position) {
        String file = position.substring(0, 1);
        String rank = position.substring(1, 2);
        validateNumeric(rank);
        FileSymbol fileSymbol = FileSymbol.getFileSymbol(file);
        return new Position(fileSymbol.getFile(), Integer.parseInt(rank));
    }

    private static void validateNumeric(String rank) {
        try {
            Integer.parseInt(rank);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Rank에 문자를 입력할 수 없습니다.");
        }
    }
}
