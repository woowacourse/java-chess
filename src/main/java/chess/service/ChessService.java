package chess.service;

import chess.dao.Move;
import chess.dao.MoveDao;
import chess.model.ChessGame;
import chess.model.Scores;
import chess.model.board.ScoreCalculator;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessGame chessGame;
    private final MoveDao moveDao;

    public ChessService(final ChessGame chessGame, final MoveDao moveDao) {
        this.chessGame = chessGame;
        this.moveDao = moveDao;
    }

    public void loadGame() {
        final List<Move> moves = findAll();

        if (existGame(moves)) {
            continueGame(moves);
        }
    }

    private List<Move> findAll() {
        return moveDao.findAll();
    }

    private boolean existGame(final List<Move> moves) {
        return !moves.isEmpty();
    }

    private void continueGame(final List<Move> moves) {
        for (Move move : moves) {
            final Position source = move.toSourcePosition();
            final Position target = move.toTargetPosition();
            chessGame.move(source, target);
        }
    }

    public void move(final Position source, final Position target) {
        chessGame.move(source, target);
        save(source, target);
    }

    public boolean isGameEnd() {
        return chessGame.isGameEnd();
    }

    public void save(final Position source, final Position target) {
        moveDao.save(source, target);
    }

    public boolean hasGame() {
        return moveDao.hasGame();
    }

    public PieceColor findCurrentPlayer() {
        return chessGame.findCurrentPlayer();
    }

    public Scores calculateScoreAll(final ScoreCalculator scoreCalculator) {
        return chessGame.calculateScoreAll(scoreCalculator);
    }

    public void truncateMove() {
        moveDao.truncateMove();
    }

    public Map<Position, Piece> getBoard() {
        return chessGame.getBoard();
    }
}
