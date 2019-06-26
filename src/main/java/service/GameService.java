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
    private static Game game = null;
    private static Optional<Position> from;

    public static Game getGame() {
        return Optional.ofNullable(game).orElseGet(() -> {
            try {
                game = new Game(GameDAO.retrieveLog());
                return game;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new FailedToRestoreGameException();
            }
        });
    }

    public static Game restartGame() {
        try {
            GameDAO.eraseLog();
            game = new Game();
            return game;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedToRestartGameException();
        }
    }

    public static String selectSrc(final String position) {
        from = Position.ofSafe(position);
        if (from.map(src -> game.isOwnPiece(src)).orElse(false)) {
            return WebView.printSelectPage(game, from.get());
        }
        return WebView.printWrongChoicePage(game, "잘못된 선택입니다.");
    }

    public static String selectDest(final String position, final Consumer<String> redirect, final Consumer<Integer> status) {
        if (!from.isPresent()) {
            return WebView.printWrongChoicePage(game, "잘못된 접근입니다.");
        }
        final Optional<Position> to = Position.ofSafe(position);
        if (to.map(dest -> game.tryToMoveFromTo(from.get(), dest)).orElse(false)) {
            return Referee.isKingAlive(game) ? initState(redirect, status) : WebView.printEndPage(game);
        }
        return WebView.printWrongChoicePage(game, from.get(), "잘못된 선택입니다.");
    }

    public static String initState(final Consumer<String> redirect, final Consumer<Integer> status) {
        from = Optional.empty();
        redirect.accept("/");
        status.accept(200);
        return null;
    }
}