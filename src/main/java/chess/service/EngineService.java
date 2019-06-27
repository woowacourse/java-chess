package chess.service;

import chess.model.ChessBoard;
import chess.model.ChessEngine;
import chess.model.GameFlow;
import chess.model.Point;
import chess.model.piece.ChessPieceColor;
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

    private Point parsePoint(final String point) {
        String x = point.substring(0, 1);
        String y = point.substring(1, 2);

        int xVal = x.charAt(0) - 96;
        int yVal = Integer.valueOf(y);

        return new Point(xVal, yVal);
    }

    public GameFlow updatePieces(final String source, final String target, ChessEngine engine, final String gameId) {
        ChessPieceDAO chessPieceDAO = ChessPieceDAO.getInstance();
        GameDAO gameDAO = GameDAO.getInstance();
        Point sourcePoint = parsePoint(source);
        Point targetPoint = parsePoint(target);

        engine.validateMove(sourcePoint, targetPoint);
        GameFlow gameFlow = engine.checkGameFlow(targetPoint);

        if (gameFlow == GameFlow.CONTINUE) {
            engine.move(sourcePoint, targetPoint);
            chessPieceDAO.updatePiece(sourcePoint, targetPoint, Integer.valueOf(gameId));
            gameDAO.setTurn(engine.getTurn(), gameId);
        }
        return gameFlow;
    }
}
