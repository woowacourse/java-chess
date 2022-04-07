package chess.domain;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;
import chess.domain.state.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessGame {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int PIECE_NAME_INDEX = 0;
    private static final int PIECE_COLOR_INDEX = 1;

    private final PieceDao pieceDao = new PieceDao();
    private final GameDao gameDao = new GameDao();

    private State state = new Ready();

    public void start() {
        state = state.start();
        pieceDao.initBoard(state.getBoard());
        gameDao.initTurn();
    }

    public void end() {
        state = state.end();

    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void move(final String[] positions) {
        final Position from = Position.from(positions[FROM]);
        final Position to = Position.from(positions[TO]);

        state = state.move(from, to);
        if (isEnd()) {
            pieceDao.deleteAllPiece();
            gameDao.initTurn();
            return;
        }
        pieceDao.updatePiece(from, to);
        gameDao.updateTurn(state.getCurrentColor());
    }

    public Status status() {
        return new Status(
                state.getWinner(),
                Map.of(Color.WHITE, state.score(Color.WHITE)),
                Map.of(Color.BLACK, state.score(Color.BLACK))
        );
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public boolean isRemovedKing() {
        return state.isRemovedKing();
    }

    public void restart() {
        final var boardData = pieceDao.findAllPiece();
        state = new Running(Color.from(gameDao.findCurrentColor()), convertToBoard(boardData));
    }

    private Board convertToBoard(final Map<String, List<String>> boardData) {
        final Map<Position, Piece> board = new HashMap<>();
        for (final Map.Entry<String, List<String>> entry : boardData.entrySet()) {
            board.put(Position.from(entry.getKey()), PieceFactory.of(entry.getValue().get(PIECE_NAME_INDEX), entry.getValue().get(PIECE_COLOR_INDEX)));
        }
        return new Board(() -> board);
    }
}
