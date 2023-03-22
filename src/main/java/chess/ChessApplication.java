package chess;

import chess.application.ChessGameService;
import chess.controller.RunningGameController;
import chess.controller.StartGameController;
import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGameRepository;
import chess.infrastructure.persistence.dao.ChessGameDao;
import chess.infrastructure.persistence.dao.PieceDao;
import chess.infrastructure.persistence.repository.JdbcChessGameRepository;

public class ChessApplication {
    public static void main(String[] args) {
        final PieceDao pieceDao = new PieceDao();
        final ChessGameDao chessGameDao = new ChessGameDao();
        final ChessGameRepository chessGameRepository = new JdbcChessGameRepository(pieceDao, chessGameDao);
        final ChessGameService chessGameService = new ChessGameService(chessGameRepository);
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final RunningGameController runningGameController = new RunningGameController(chessGameService);
        new StartGameController(chessGameService, chessBoardFactory, runningGameController).start();
    }
}
