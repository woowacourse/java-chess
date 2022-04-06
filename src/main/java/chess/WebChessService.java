package chess;

import chess.dao.BoardDao;
import chess.dao.ChessDao;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.state.State;
import java.util.Map;

public class WebChessService {

    private final ChessDao chessDao = new ChessDao();
    private final BoardDao boardDao = new BoardDao();

    public void initializeGame(State state) {
        chessDao.deleteAll();
        chessDao.saveState(state);

        boardDao.deleteAll();
        Board board = state.getBoard();
        Map<String, Piece> map = board.toMap();
        for (String location : map.keySet()) {
            boardDao.addPiece(location, map.get(location));
        }
    }
}
