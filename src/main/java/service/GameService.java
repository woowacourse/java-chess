package service;

import model.board.Position;
import model.game.FailedToRestartGameException;
import model.game.FailedToRestoreGameException;
import model.game.Game;
import model.game.GameDAO;
import model.game.Referee;
import view.WebView;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;

public class GameService {
    private static Optional<Game> game = Optional.empty();
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

    public static String selectSrc(final String position) {
        from = Position.ofSafe(position);
        if (from.map(pos -> game.get().isOwnPiece(pos)).orElse(false)) {
            return WebView.printSelectPage(game.get(), from.get());
        }
        return WebView.printWrongChoicePage(game.get(), "잘못된 선택입니다.");
    }

    public static String selectDest(final String position, final Consumer<String> redirect, final Consumer<Integer> status) {
        if (!from.isPresent()) {
            return WebView.printWrongChoicePage(game.get(), "잘못된 접근입니다.");
        }
        final Optional<Position> to = Position.ofSafe(position);
        if (to.map(pos -> game.get().tryToMoveFromTo(from.get(), to.get())).orElse(false)) {
            return Referee.isKingAlive(game.get()) ? initState(redirect, status) : WebView.printEndPage(game.get());
        }
        return WebView.printWrongChoicePage(game.get(), from.get(), "잘못된 선택입니다.");
    }

    public static String initState(final Consumer<String> redirect, final Consumer<Integer> status) {
        from = Optional.empty();
        redirect.accept("/");
        status.accept(200);
        return null;
    }
}

//// TODO: 2019-06-26 refactor 