package chess.database;

import chess.web.ChessCommand;

import java.sql.SQLException;
import java.util.List;

public interface ChessDao {
    void addCommand(ChessCommand command) throws SQLException;

    void clearCommands() throws SQLException;

    List<ChessCommand> selectCommands() throws SQLException;
}