package chess.websocket.commander;

import chess.websocket.util.TriConsumer;
import java.io.IOException;
import java.util.Arrays;
import org.eclipse.jetty.websocket.api.Session;

public enum RequestCommand {

    ENTER("enter", (RequestCommander::enterRoom)),
    PIECES("pieces", RequestCommander::initialPieces),
    MOVE("move", RequestCommander::move);

    private static final int COMMAND_INDEX = 1;
    private final String command;
    private final TriConsumer<RequestCommander, String[], Session> consumer;

    RequestCommand(String command, TriConsumer<RequestCommander, String[], Session> consumer) {

        this.command = command;
        this.consumer = consumer;
    }

    public static void execute(RequestCommander requestCommander, String[] command, Session session)
        throws IOException {
        Arrays.stream(values())
            .filter(value -> value.command.equals(command[COMMAND_INDEX]))
            .findAny()
            .orElseThrow(IllegalArgumentException::new)
            .consumer.accept(requestCommander, command, session);
    }
}
