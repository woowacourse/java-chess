package chess.controller.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CommandControllerFactoryTest {
    
    @ParameterizedTest(name = "커맨드 메뉴에 존재하는 커맨드 입력 테스트")
    @ValueSource(strings = {"start", "move", "status", "end", "exit"})
    void hasCommand(String command) {
        
        // when
        boolean hasCommand = CommandControllerFactory.hasCommand(command);
        
        // then
        assertThat(hasCommand).isTrue();
    }
    
    @Test
    @DisplayName("커맨드 메뉴에 존재하지 않는 커맨드 입력 테스트")
    void hasCommand_DoesNotHaveCommand_False() {
        
        // given
        String command = "test";
    
        // when
        boolean hasCommand = CommandControllerFactory.hasCommand(command);
    
        // then
        assertThat(hasCommand).isFalse();
    }
    
    @ParameterizedTest(name = "입력 커맨드에 따른 커맨드 컨트롤러 생성 테스트")
    @MethodSource("generateCommandSource")
    void createFrom(String[] command, Class<CommandController> controllerClass) {
        
        // when
        CommandController commandController = CommandControllerFactory.createFrom(command);
        
        // then
        assertThat(commandController).isInstanceOf(controllerClass);
    }
    
    private static Stream<Arguments> generateCommandSource() {
        return Stream.of(
                Arguments.of(new String[]{"start"}, StartController.class),
                Arguments.of(new String[]{"move", "b2", "b4"}, MoveController.class),
                Arguments.of(new String[]{"status"}, StatusController.class),
                Arguments.of(new String[]{"end"}, EndController.class),
                Arguments.of(new String[]{"exit"}, ExitController.class)
        );
    }
}