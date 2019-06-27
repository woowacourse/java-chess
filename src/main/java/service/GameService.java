package service;

import model.Position;
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
    private static Optional<Position> from = Optional.empty();

    public static Game getGame() {
        return game.orElseGet(() -> {
            try {
                game = Optional.of(new Game(GameDAO.retrieveLog()));
                return game.get();
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

    public static String selectSource(final String source) {
        from = Optional.ofNullable(Position.of(source));
        if (from.map(pos -> game.get().isOwnPiece(pos)).orElse(false)) {
            return WebView.printSelectPage(game.get(), from.get());
        }
        return WebView.printWrongChoicePage(game.get(), "잘못된 선택입니다.");
    }

    public static String selectDest(final String destination, final Consumer<String> redirect, final Consumer<Integer> status) {
        final Optional<Position> to = Optional.ofNullable(Position.of(destination));
        game.get().movePiece(from.get(), to.get());
//        game.get().board().getPieceAt(from.get()).moveTo(to.get());
        return initState(redirect, status);
//        if (to.map(pos -> game.get().tryToMoveFromTo(from.get(), to.get())).orElse(false)) {
//            return Referee.isKingAlive(game.get()) ? initState(redirect, status) : WebView.printEndPage(game.get());
//        }
    }

    public static String initState(final Consumer<String> redirect, final Consumer<Integer> status) {
        from = Optional.empty();
        redirect.accept("/");
        status.accept(200);
        return null;
    }
}

