package chess.service;

import chess.dao.SquareDao;
import chess.dao.StateDao;
import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.dto.ResultDto;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.Ready;
import chess.model.state.State;
import chess.service.converter.StateToString;
import chess.service.converter.StringToState;
import java.util.HashMap;
import java.util.Map;

public class GameService {

    private final SquareDao squareDao;
    private final StateDao stateDao;

    public GameService(final SquareDao squareDao, final StateDao stateDao) {
        this.squareDao = squareDao;
        this.stateDao = stateDao;
    }

    public BoardDto start() {
        deleteData();
        State state = new Ready();
        stateDao.save(StateToString.convert(state));
        fromBoard(state.getBoard());
        return BoardDto.from(state.getBoard());
    }

    public BoardDto end() {
        Board board = toBoard(squareDao.find());
        deleteData();
        return BoardDto.from(board.getBoard());
    }

    public BoardDto move(MoveDto moveDto) {
        String source = moveDto.getSource();
        String target = moveDto.getTarget();

        State state = proceed(moveDto);

        squareDao.update(source, state.getBoard().get(Position.from(source)));
        squareDao.update(target, state.getBoard().get(Position.from(target)));

        return BoardDto.from(state.getBoard());
    }

    private State proceed(MoveDto moveDto) {
        Board board = toBoard(squareDao.find());
        String nowStateName = stateDao.find();
        State nowState = StringToState.convert(nowStateName, board);

        State nextState = nowState.proceed(moveDto.getCommand());
        String nextStateName = StateToString.convert(nextState);
        stateDao.update(nowStateName, nextStateName);

        return nextState;
    }

    public ResultDto status(String command) {
        Board board = toBoard(squareDao.find());
        String nowStateName = stateDao.find();
        stateDao.update(nowStateName, command);
        State nextState = StringToState.convert(stateDao.find(), board);
        return new ResultDto(nextState.getScore(), nextState.getWinner());
    }

    public BoardDto load() {
        Board board = toBoard(squareDao.find());
        return BoardDto.from(board.getBoard());
    }

    private void deleteData() {
        squareDao.delete();
        stateDao.delete();
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
