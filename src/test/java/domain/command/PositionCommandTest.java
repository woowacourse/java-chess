package domain.command;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionCommandTest {

    @Test
    @DisplayName("출발 위치를 반환한다.")
    void parse_MoveCommand_Source() {
        String rawCommand = "b2 b3";

        PositionCommand positionCommand = new PositionCommand(rawCommand);
        Position source = positionCommand.sourcePosition();

        assertThat(source).isEqualTo(new Position(File.B, Rank.TWO));
    }

    @Test
    @DisplayName("도착 위치를 반환한다.")
    void parse_MoveCommand_Target() {
        String rawCommand = "b2 b3";

        PositionCommand positionCommand = new PositionCommand(rawCommand);
        Position source = positionCommand.targetPosition();

        assertThat(source).isEqualTo(new Position(File.B, Rank.THREE));
    }
}
