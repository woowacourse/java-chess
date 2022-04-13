package chess.repository;

import chess.dao.SquareDao;
import chess.dao.StateDao;
import chess.dto.MoveDto;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.State;
import java.util.HashMap;
import java.util.Map;

public class GameRepositoryImpl implements GameRepository {

    private final SquareDao squareDao;
    private final StateDao stateDao;

    public GameRepositoryImpl(SquareDao squareDao, StateDao stateDao) {
        this.squareDao = squareDao;
        this.stateDao = stateDao;
    }

    public void initGameData(State state) {
        stateDao.save(state);
        fromBoard(state.getBoard());
    }

    public void saveGameData(State nextState, MoveDto moveDto) {
        State nowState = this.getState();
        stateDao.update(nowState, nextState);

        String source = moveDto.getSource();
        String target = moveDto.getTarget();
        Map<Position, Piece> squares = nextState.getBoard();

        squareDao.update(source, squares.get(Position.from(source)));
        squareDao.update(target, squares.get(Position.from(target)));
    }

    public void deleteGameData() {
        squareDao.delete();
        stateDao.delete();
    }

    public Board getBoard() {
        Board board = toBoard(squareDao.find());
        return board;
    }

    public State getState() {
        Board board = toBoard(squareDao.find());
        return stateDao.find(board);
    }

    private void fromBoard(Map<Position, Piece> board) {
        board.keySet()
                .forEach(position -> squareDao.save(position, board.get(position)));
    }

    private Board toBoard(Map<String, String> squares) {
        Map<Position, Piece> board = new HashMap<>();
        for (String key : squares.keySet()) {
            board.put(Position.from(key), Piece.getPiece(squares.get(key)));
        }
        return Board.from(board);
    }
}
