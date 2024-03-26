package domain.command;

import domain.position.Position;
import domain.position.PositionGenerator;

import java.util.List;

public class PositionCommand {

    private final PositionGenerator positionParser = new PositionGenerator();
    private final List<String> positions;

    public PositionCommand(String positions) {
        this.positions = List.of(positions.split(" "));
    }

    public PositionCommand() {
        this("");
    }

    public Position sourcePosition() {
        if (positions.isEmpty()) {
            throw new IndexOutOfBoundsException("[ERROR] 위치 명령어는 비어있을 수 없습니다.");
        }
        return positionParser.generate(positions.get(0));
    }

    public Position targetPosition() {
        if (positions.size() != 2) {
            throw new IndexOutOfBoundsException("[ERROR] 올바르지 않은 값입니다.");
        }
        return positionParser.generate(positions.get(1));
    }
}
