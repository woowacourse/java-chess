package chess.service;

import chess.dao.MoveDaoImpl;
import chess.model.ChessGame;

public class ChessServiceFixture {

    public static ChessService createService() {
        return new ChessService(new ChessGame(), new MoveDaoImpl());
    }
}
