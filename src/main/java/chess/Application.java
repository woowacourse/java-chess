package chess;

import chess.controller.ChessGameController;
import chess.domain.dao.PieceDaoImpl;
import chess.domain.dao.TurnDaoImpl;

public class Application {

    public static void main(String[] args) {
        new ChessGameController(new TurnDaoImpl(), new PieceDaoImpl()).run();
    }

}
