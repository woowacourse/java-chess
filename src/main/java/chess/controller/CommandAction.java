package chess.controller;

import chess.dto.CommandRequest;

public interface CommandAction {

    AppStatus execute(CommandRequest commandRequest);
    
}
