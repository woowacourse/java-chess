package chess;

import chess.controller.ChessHandler;
import chess.service.ChessBoardService;
import chess.service.PieceService;
import chess.service.PositionService;

public final class ChessApplication {
    public static void main(String[] args) {
        final PieceService pieceService = new PieceService();
        final PositionService positionService = new PositionService();
        final ChessBoardService chessBoardService = new ChessBoardService(pieceService, positionService);
        final ChessHandler chessHandler = new ChessHandler(chessBoardService);
        chessHandler.run();
    }
}
