package chess.dao;

import chess.dto.CommandDto;

import java.util.List;

public interface ChessDao {
    void addCommand(CommandDto command);

    void clearCommands();

    List<CommandDto> selectCommands();
}