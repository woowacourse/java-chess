package chess.service;

import chess.dao.BoardDaoImpl;
import chess.dao.TurnDaoImpl;

public class DependencyFactory {

    public static ChessService chessService() {
        return new ChessService(new BoardDaoImpl(), new TurnDaoImpl());
    }
}
