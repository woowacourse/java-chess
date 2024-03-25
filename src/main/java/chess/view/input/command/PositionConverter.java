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
        return new Position(FileSymbol.convertFile(file), Integer.parseInt(rank));
    }

    private static void validateNumeric(String file) {
        try {
            Integer.parseInt(file);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Rank는 숫자 1 부터 8까지 입력할 수 있습니다.");
        }
    }
}
