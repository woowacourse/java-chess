package chess;

import chess.dao.BoardDao;
import chess.dao.ChessDao;
import chess.dao.StateConverter;
import chess.domain.board.Board;
import chess.domain.piece.EmptyPiece;
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
        Map<String, Piece> map = state.getBoard().toMap();
        for (String location : map.keySet()) {
            boardDao.addPiece(location, map.get(location));
        }
    }

    public void move(String source, String target) {
        Piece sourcePiece = boardDao.getPiece(source);
        boardDao.updatePiece(source, new EmptyPiece());
        boardDao.updatePiece(target, sourcePiece);
    }

    public void updateState(State state) {
        chessDao.updateState(state);
    }

    public State getState() {
        String state = chessDao.getState();
        Board board = new Board(boardDao.getBoardMap());
        return StateConverter.of(state, board);
    }
}
