package techcourse.fp.chess.dto.request;

import java.util.List;

public class CommandRequest {

    private final String message;
    private String source;
    private String target;

    public CommandRequest(final String message) {
        if (message.equalsIgnoreCase("move")) {
            throw new IllegalArgumentException("올바른 형식의 커맨드를 입력하세요.");
        }
        this.message = message;
    }

    public CommandRequest(final String message, final String source, final String target) {
        this.message = message;
        validateLength(source, target);
        this.source = source;
        this.target = target;
    }

    private void validateLength(final String source, final String target) {
        if (source.length() != 2 || target.length() != 2) {
            throw new IllegalArgumentException("시작점과 도착점을 정확하게 입력해주세요.");
        }
    }

    public static CommandRequest create(List<String> commands) {
        if (commands.size() == 1) {
            return new CommandRequest(commands.get(0));
        }

        if (commands.size() == 3) {
            return new CommandRequest(commands.get(0), commands.get(1), commands.get(2));
        }

        throw new IllegalArgumentException("올바른 형식으로 커맨드를 입력해주세요.");
    }

    public String getMessage() {
        return message;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
