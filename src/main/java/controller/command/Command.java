package controller.command;

import view.format.command.PlayCommandFormat;

import java.sql.SQLException;


public interface Command {

    void executeStart() throws SQLException;

    void executePlay(PlayCommandFormat playCommandFormat, int gameId) throws SQLException;
}
