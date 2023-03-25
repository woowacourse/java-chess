package chess.model.board.state;

import chess.controller.GameCommand;
import chess.dao.Move;
import chess.dao.MoveDao;
import chess.dao.MoveFindAllStrategy;
import chess.model.ChessGame;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public abstract class ProgressState implements GameState {

    protected final ChessGame chessGame;
    protected final MoveDao moveDao;

    protected ProgressState(final ChessGame chessGame, final MoveDao moveDao) {
        this.chessGame = chessGame;
        this.moveDao = moveDao;
    }

    public static GameState of(final GameCommand gameCommand, final MoveDao moveDao) {
        if (gameCommand.isMove()) {
            throw new IllegalArgumentException("시작하기 전에 move를 호출 할 수 없습니다.");
        }

        if (gameCommand.isEnd()) {
            return new End();
        }

        return createProgressState(gameCommand, moveDao);
    }

    private static ProgressState createProgressState(final GameCommand gameCommand, final MoveDao moveDao) {
        final ChessGame chessGame = createChessGame(moveDao);

        if (gameCommand.isStatus()) {
            return new Status(chessGame, moveDao);
        }

        return new Playing(chessGame, moveDao);
    }

    private static ChessGame createChessGame(final MoveDao moveDao) {
        final List<Move> moves = moveDao.findAll(new MoveFindAllStrategy());
        final ChessGame chessGame = new ChessGame();

        if (existGame(moves)) {
            continueGame(moves, chessGame);
        }

        return chessGame;
    }

    private static boolean existGame(final List<Move> moves) {
        return !moves.isEmpty();
    }

    private static void continueGame(final List<Move> moves, final ChessGame chessGame) {
        for (Move move : moves) {
            final Position source = move.toSourcePosition();
            final Position target = move.toTargetPosition();
            chessGame.move(source, target);
        }
    }

    @Override
    public abstract boolean isStatus();

    @Override
    public final GameState changeState(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            throw new IllegalArgumentException("게임 도중에 start를 입력할 수 없습니다.");
        }

        return modifyState(gameCommand);
    }

    private GameState modifyState(final GameCommand gameCommand) {
        if (gameCommand.isEnd()) {
            return new End();
        }

        if (gameCommand.isStatus()) {
            return new Status(chessGame, moveDao);
        }

        return new Playing(chessGame, moveDao);
    }

    @Override
    public final boolean isNotEnd() {
        return true;
    }

    @Override
    public final GameState isGameEnd() {
        if ((chessGame.isGameEnd())) {
            return new End();
        }

        return this;
    }

    @Override
    public final Map<Position, Piece> getBoard() {
        return chessGame.getBoard();
    }
}
