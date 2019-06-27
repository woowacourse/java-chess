package service;

import model.board.Position;
import model.game.*;
import view.WebView;

import java.util.Optional;
import java.util.function.Consumer;

public class GameService {
    private static Game game = null;
    private static Optional<Position> src;

    public static Game getGame() {
        return Optional.ofNullable(game).orElseGet(() -> {
            try {
                game = new Game(GameDAO.retrieveLog());
                return game;
            } catch (FailedToRetrieveLogException e) {
                throw new FailedToRestoreGameException(e);
            }
        });
    }

    public static Game restartGame() {
        try {
            GameDAO.eraseLog();
            game = new Game();
            return game;
        } catch (FailedToEraseLogException e) {
            throw new FailedToRestartGameException(e);
        }
    }

    public static String selectSrc(final String input) {
        src = Position.ofSafe(input);
        if (src.map(pos -> game.isOwnPiece(pos)).orElse(false)) {
            return WebView.printSelectPage(game, src.get());
        }
        return WebView.printWrongChoicePage(game, "잘못된 선택입니다.");
    }

    public static String selectDest(final String input, final Consumer<String> redirect, final Consumer<Integer> status) {
        if (!src.isPresent()) {
            return WebView.printWrongChoicePage(game, "잘못된 접근입니다.");
        }
        if (Position.ofSafe(input).map(dest -> game.tryToMoveFromTo(src.get(), dest)).orElse(false)) {
            return Referee.isKingAlive(game) ? initState(redirect, status) : WebView.printVictoryPage(game);
        }
        return WebView.printWrongDestinationPage(game, src.get(), "잘못된 선택입니다.");
    }

    public static String initState(final Consumer<String> redirect, final Consumer<Integer> status) {
        src = Optional.empty();
        redirect.accept("/");
        status.accept(200);
        return null;
    }
}