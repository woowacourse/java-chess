package view;

import java.util.Arrays;

public enum BootingCommand {
    START("start"),
    END("end");

    private final String command;

    BootingCommand(String command) {
        this.command = command;
    }

    public static BootingCommand findByCommand(String command) {
        return Arrays.stream(BootingCommand.values())
                .filter(bootingCommand -> bootingCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력입니다."));
    }

    public boolean isStart() {
        return this == START;
    }
}
