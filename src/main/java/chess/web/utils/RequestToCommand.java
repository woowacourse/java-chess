package chess.web.utils;

import chess.web.dto.MoveCommand;
import com.google.gson.Gson;

public class RequestToCommand {

    public static MoveCommand toMoveCommand(final String body) {
        Gson gson = new Gson();
        return gson.fromJson(body, MoveCommand.class);
    }
}
