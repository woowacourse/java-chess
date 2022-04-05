package chess.utils;

import chess.dto.MoveCommand;
import com.google.gson.Gson;

public class RequestToCommand {

    public static MoveCommand toMoveCommand(final String body) {
        Gson gson = new Gson();
        return gson.fromJson(body, MoveCommand.class);
    }
}
