package chess.domain.manager;

import chess.converter.PiecesConverter;
import chess.dao.dto.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.InitBoardInitializer;
import chess.domain.board.LoadBoardInitializer;
import chess.domain.statistics.ChessGameStatistics;

import static chess.converter.PiecesConverter.convertSquares;
import static chess.domain.piece.attribute.Color.WHITE;

public class ChessGameManagerFactory {
    private ChessGameManagerFactory() {
    }

    public static ChessGameManager createRunningGame(long id) {
        return new RunningGameManager(id, InitBoardInitializer.getBoard(), WHITE);
    }

    public static ChessGameManager createEndGame(long id, ChessGameStatistics chessGameStatistics, Board board) {
        return new EndChessGameManager(id, chessGameStatistics, board);
    }

    public static ChessGameManager createNotStartedGameManager(long id) {
        return new NotStartedChessGameManager(id);
    }

    public static ChessGameManager loadingGame(ChessGame chessGame) {
        Board loadedBoard = LoadBoardInitializer.getBoard(convertSquares(chessGame.getPieces()));
        if (chessGame.isRunning()) {
            return new RunningGameManager(chessGame.getId(), loadedBoard, chessGame.getNextTurn());
        }
        return createEndGame(chessGame.getId(), ChessGameStatistics.createNotStartGameResult(),
                LoadBoardInitializer.getBoard(PiecesConverter.convertSquares(chessGame.getPieces())));
    }
}
