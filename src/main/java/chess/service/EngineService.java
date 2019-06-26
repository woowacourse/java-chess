package chess.service;

import chess.model.*;
import chess.model.coordinate.Point;
import chess.persistance.ChessPieceDAO;
import chess.persistance.GameDAO;

public class EngineService {
    private static final EngineService INSTANCE = new EngineService();

    private EngineService() {
    }


    public static EngineService getInstance() {
        return INSTANCE;
    }

    public ChessEngine getEngine(final ChessBoard board, final ChessPieceColor turn) {
        return new ChessEngine(board, turn);
    }

    public GameFlow validateMove(final ChessEngine engine, final String source, final String target) {
        return engine.move(parsePoint(source), parsePoint(target));
    }

    private Point parsePoint(final String point) {
        String x = point.substring(0, 1);
        String y = point.substring(1, 2);

        int xVal = x.charAt(0) - 96;
        int yVal = Integer.valueOf(y);

        return new Point(xVal, yVal);
    }

    public void updatePieces(final String source, final String target, ChessEngine engine, final String gameId) {
        ChessPieceDAO chessPieceDAO = ChessPieceDAO.getInstance();
        System.out.println(source + " / " + target + " / " + gameId);
        chessPieceDAO.updatePiece(parsePoint(source), parsePoint(target), Integer.valueOf(gameId));
        GameDAO gameDAO = GameDAO.getInstance();
        gameDAO.setTurn(engine.getTurn(), gameId);
    }
}
