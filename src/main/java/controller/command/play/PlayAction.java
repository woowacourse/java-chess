package controller.command.play;

import database.connection.ConnectionGenerator;
import domain.ChessGame;
import java.util.Map;

public interface PlayAction {
    boolean execute(final ChessGame chessGame, final Map<Integer, String> parameters,
                    final ConnectionGenerator connectionGenerator);
}
