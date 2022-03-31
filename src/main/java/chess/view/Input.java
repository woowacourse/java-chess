package chess.view;

public final class Input {

    private Input() {
    }

    public static String inputCommand(final Enterable enterable) {
        final String command = enterable.enter();
        validateBlank(command);
        return command;
    }

    private static void validateBlank(final String command) {
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요!");
        }
    }
}
