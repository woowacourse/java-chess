package chess;

import chess.controller.CommandProcessor;
import chess.controller.Controller;
import chess.controller.ResultProcessor;
import chess.dao.GameDao;
import chess.dao.ConnectionGenerator;
import chess.domain.state.StateProcessor;

import java.sql.SQLException;

public final class ChessGameApplication {
    public static void main(String[] args) throws SQLException {

        CommandProcessor commandProcessor = CommandProcessor.create();
        StateProcessor stateProcessor = StateProcessor.from(new GameDao(ConnectionGenerator.getConnection()));
        ResultProcessor resultProcessor = ResultProcessor.create();

        Controller controller = new Controller(commandProcessor, stateProcessor, resultProcessor);
        controller.run();

    }
}
