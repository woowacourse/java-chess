package service;

import model.board.Position;
import model.game.FailedToRestartGameException;
import model.game.FailedToRestoreGameException;
import model.game.Game;
import model.game.GameDAO;
import view.WebView;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;

public class GameService {
    private static Optional<Game> game = Optional.empty();
    static {
        game = Optional.of(getGame());
    }
    private static Optional<Position> from = Optional.empty();

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
        return WebView.printWrongChoicePage(game.get(), "잘못된 선택입니다. 1saf");
    }

    public static String selectDest(String position, Consumer<String> redirect, Consumer<Integer> status) {
        if (!from.isPresent()) {
            return WebView.printWrongChoicePage(game.get(), "잘못된 접근입니다.");
        }
        final Optional<Position> to = Position.ofSafe(position);
        if (to.map(pos -> game.get().tryToMoveFromTo(from.get(), to.get())).orElse(false)) {
            reselectSrc(redirect, status);
        }
        return WebView.printWrongChoicePage(game.get(), from.get(), "잘못된 선택입니다. 2sdgasdg");
    }

    public static String reselectSrc(Consumer<String> redirect, Consumer<Integer> status) {
        from = Optional.empty();
        redirect.accept("/");
        status.accept(200);
        return null;
    }
}