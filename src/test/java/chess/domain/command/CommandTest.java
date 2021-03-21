package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandTest {
    @DisplayName("입력 받은 String 또는 String 배열로 명령어 객체를 생성한다.")
    @Test
    void String으로_명령어_객체_생성() {
        Command command1 = new Command("move a1 a2");
        String[] splitCommands = new String[]{"move", "a1", "a2"};
        Command command2 = new Command(splitCommands);

        assertThat(command1.sourceAndTarget())
                .isEqualTo(command2.sourceAndTarget());
    }

    @DisplayName("first-command 객체와 string 배열로 명령어 객체를 생성한다.")
    @Test
    void firstCommand_객체로_명령어_객체_생성() {
        String firstCommandString = "move";
        FirstCommand firstCommand = FirstCommand.findFirstCommand(firstCommandString);
        String[] splitCommands = new String[]{"move", "a1", "a2"};

        Command command1 = new Command(firstCommandString, splitCommands);
        Command command2 = new Command(firstCommand, splitCommands);

        assertThat(command1.sourceAndTarget())
                .isEqualTo(command2.sourceAndTarget());
    }

    @DisplayName("move 명령어의 형식을 검증한다. - 3개 단위가 아닐 경우 예외")
    @Test
    void move_명령어_형식_검증() {
        Command command = new Command("move a1 a2 a4");

        assertThatThrownBy(() -> command.sourceAndTarget())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
