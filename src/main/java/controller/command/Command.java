package controller.command;

import controller.status.ChessProgramStatus;

import java.sql.SQLException;
import java.util.List;


public interface Command {

    ChessProgramStatus executeStart() throws SQLException;

    ChessProgramStatus executePlay(List<String> inputs, int gameId) throws SQLException;
}
