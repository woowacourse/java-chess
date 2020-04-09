package chess.database;

import chess.web.ChessCommand;

import java.sql.SQLException;
import java.util.List;

public interface ChessDao {
    void addCommand(ChessCommand command);

    void clearCommands();

    List<ChessCommand> selectCommands();
}