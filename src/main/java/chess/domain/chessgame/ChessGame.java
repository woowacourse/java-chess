package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ChessGame {
    private static final String DEFAULT_GAME_ID = "game_001";

    private final String gameId;
    private final Board board;
    private final Turn turn;
    private final GameState gameState;

    public ChessGame(String gameId, Board board, Turn turn, GameState gameState) {
        this.gameId = gameId;
        this.board = board;
        this.turn = turn;
        this.gameState = gameState;
    }

    public ChessGame(Board board) {
        this(DEFAULT_GAME_ID, board, new Turn(), new GameState());
    }

    public ChessGame() {
        this(new Board());
    }

    public ChessGame(String game_id, String pieces, String turn) {
        this(game_id, BoardInitializer.boardFromString(pieces), new Turn(turn), new GameState());
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public void move(final Position source, final Position target) {
        board.move(source, target, turn.now());
        prepareNextTurn();
    }

    private void prepareNextTurn() {
        if (board.isKingDead()) {
            endGame();
            return;
        }
        turn.next();
    }

    public void endGame() {
        gameState.endGame();
    }

    public void restartGame() {
        board.refresh();
        turn.refresh();
        gameState.refresh();
    }

    public Map<Position, Piece> board() {
        return board.unwrap();
    }

    public String boardForDAO() {
        return board.unwrap().values()
                .stream()
        .map(Piece::name)
        .collect(Collectors.joining(","));
    }

    public String turnForDAO() {
        return turn.now().teamName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame game = (ChessGame) o;
        return gameId.equals(game.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }
}
