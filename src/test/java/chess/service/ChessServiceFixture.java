package chess.service;

import chess.dao.FakeMoveDao;
import chess.model.ChessGame;

public class ChessServiceFixture {

    public static ChessService createService() {
        return new ChessService(new ChessGame(), new FakeMoveDao());
    }
}
