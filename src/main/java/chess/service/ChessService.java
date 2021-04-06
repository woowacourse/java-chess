package chess.service;

import chess.Dao.MoveDao;
import chess.Dto.ScoreDto;
import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.state.Running;
import java.util.Map;

public class ChessService implements Service {

    private final MoveDao moveDao = new MoveDao();

    @Override
    public Game findGame() {
        Game game = new Game();
        game.changeState(new Running());
        Map<Position, Position> moves = moveDao.getMoves();
        moves.forEach(game::move);
        return game;
    }

    @Override
    public Board findBoard() {
        return findGame().getBoard();
    }

    @Override
    public boolean addMove(Position from, Position to) {
        Board board = findBoard();
        boolean movable = board.isMovable(to, board.generateAvailablePath(board.findPieceBy(from)));
        if (movable) {
            findGame().move(from, to);
            moveDao.addMove(from, to);
        }
        return movable;
    }

    @Override
    public Board restartBoard() {
        moveDao.deleteAll();
        return findBoard();
    }

    @Override
    public boolean endGame() {
        return findGame().isFinished();
    }

    @Override
    public PieceColor findTurn() {
        return findGame().getTurn();
    }

    @Override
    public Map<PieceColor, Double> getScores() {
        return new ScoreDto(findBoard()).getScores();
    }
}
