package chess.dao;

import chess.dto.BoardDto;
import chess.dto.ResultDto;
import chess.model.Team;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.Map;

public class GameService {

    private final SquareDao squareDao;
    private final TurnDao turnDao;

    public GameService(final SquareDao squareDao, final TurnDao turnDao) {
        this.squareDao = squareDao;
        this.turnDao = turnDao;
    }

    public BoardDto start() {
        deleteData();
        fromBoard(Board.init().getBoard());
        Board board = toBoard(squareDao.find());
        turnDao.save("WHITE");
        return BoardDto.from(board.getBoard());
    }

    public BoardDto end() {
        deleteData();
        Board board = toBoard(squareDao.find());
        return BoardDto.from(board.getBoard());
    }

    public BoardDto move(String source, String target) {
        String nowTurn = turnDao.find();
        Board board = movePieceInBoard(source, target, nowTurn);

        squareDao.update(source, board.getBoard().get(Position.from(source)));
        squareDao.update(target, board.getBoard().get(Position.from(target)));

        String nextTurn = Team.of(nowTurn).getOpponentTeam();
        turnDao.update(nowTurn, nextTurn);

        Board movedBoard = toBoard(squareDao.find());
        return BoardDto.from(movedBoard.getBoard());
    }

    public ResultDto status() {
        Board board = toBoard(squareDao.find());
        return new ResultDto(board.getScore(), board.getWinner());
    }

    public BoardDto load() {
        Board board = toBoard(squareDao.find());
        return BoardDto.from(board.getBoard());
    }

    private Board movePieceInBoard(String source, String target, String turn) {
        Board board = toBoard(squareDao.find());
        board.checkSameTeam(Team.of(turn), Position.from(source));
        board.move(Position.from(source), Position.from(target));
        return board;
    }

    private void deleteData() {
        squareDao.delete();
        turnDao.delete();
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
