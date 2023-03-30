package chess.controller;

import chess.domain.GameRoom;
import java.util.List;

@FunctionalInterface
public interface CommandAction {
    Command get(List<String> inputs, GameRoom gameRoom);
}
