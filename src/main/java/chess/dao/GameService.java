package chess.dao;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.State;
import java.util.HashMap;
import java.util.Map;

public class GameService {

    private final StateDao stateDao;
    private final SquareDao squareDao;

    public GameService() {
        this.stateDao = new StateDao();
        this.squareDao = new SquareDao();
    }

    public Map<Position, Piece> loadGameBoard() {
        String stateName = stateDao.find();
        Map<Position, Piece> board = createBoard();
        State state = StateGenerator.generateState(stateName, board);
        return state.getBoard();
    }

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = new HashMap<>();
        Map<String, String> squares = squareDao.find();
        for (String position : squares.keySet()) {
            board.put(Position.from(position), Piece.getPiece(squares.get(position)));
        }
        return board;
    }

    public void startGame() {
        stateDao.delete();
        stateDao.save("whiteturn");
        squareDao.delete();
        squareDao.save(Board.init().getBoard());
    }

    public void endGame() {
        stateDao.delete();
        squareDao.delete();
    }
}
