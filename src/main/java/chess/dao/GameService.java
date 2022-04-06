package chess.dao;

import chess.dto.BoardDto;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.Map;

public class GameService {

    private final SquareDao squareDao;
    private final TurnDao turnDao;

    public GameService() {
        this.squareDao = new SquareDao();
        this.turnDao = new TurnDao();
    }

    public BoardDto startGame() {
        deleteData();
        fromBoard(Board.init().getBoard());
        Board board = toBoard(squareDao.find());
        turnDao.save("white");
        return BoardDto.from(board.getBoard());
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
