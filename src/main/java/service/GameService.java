package service;

import model.board.Position;
import model.game.FailedToRestartGameException;
import model.game.FailedToRestoreGameException;
import model.game.Game;
import model.game.GameDAO;
import spark.Response;
import view.WebView;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameService {
    private static Optional<Game> game = Optional.empty();
    static {
        game = Optional.of(getGame());
    }
    private static Optional<Position> from;
    private static Optional<Position> to;

    public static Game getGame() {
        return game.orElseGet(() -> {
            try {
                return new Game(GameDAO.retrieveLog());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new FailedToRestoreGameException();
            }
        });
    }

    public static Game restartGame() {
        try {
            GameDAO.eraseLog();
            game = Optional.of(new Game());
            return getGame();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedToRestartGameException();
        }
    }

    public static String selectSrc(String position) {
        from = Position.ofSafe(position);
        if (from.map(pos -> game.get().isOwnPiece(pos)).orElse(false)) {
            return WebView.printSelectPage(game.get(), from.get());
        }
        return WebView.printWrongChoicePage(game.get());
    }

    public static String selectDest(String position, Consumer<String> redirect, Consumer<Integer> status) {
        to = Position.ofSafe(position);
        if (to.map(pos -> game.get().tryToMoveFromTo(from.get(), to.get())).orElse(false)) {
            redirect.accept("/");
            status.accept(200);
            return null;
        }
        return WebView.printWrongChoicePage(game.get(), from.get());
    }
}