package chess.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Request {

    private final List<String> command;

    private Request(List<String> command) {
        this.command = command;
    }

    public static Request from(String body) {
        return new Request(
                Arrays.stream(body.replace("command=", "")
                        .replace("\r\n", "")
                        .split(" ")).collect(Collectors.toList()));
    }

    public List<String> getCommand() {
        return command;
    }
}
