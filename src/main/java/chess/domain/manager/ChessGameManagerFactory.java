package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.board.InitBoardInitializer;
import chess.domain.board.LoadBoardInitializer;
import chess.domain.piece.attribute.Color;
import chess.domain.statistics.ChessGameStatistics;

import static chess.converter.PiecesConverter.convertSquares;
import static chess.domain.piece.attribute.Color.WHITE;

public class ChessGameManagerFactory {
    private ChessGameManagerFactory() {
    }

    public static ChessGameManager createRunningGame(long id) {
        return new RunningGameManager(id, InitBoardInitializer.getBoard(), WHITE);
    }

    public static ChessGameManager createEndGame(long id, ChessGameStatistics chessGameStatistics) {
        return new EndChessGameManager(id, chessGameStatistics);
    }

    public static ChessGameManager createNotStartedGameManager(long id) {
        return new NotStartedChessGameManager(id);
    }

    public static ChessGameManager loadingGame(boolean isRunning, Long id, String pieces, Color nextTurn) {
        Board loadedBoard = LoadBoardInitializer.getBoard(convertSquares(pieces));
        if (isRunning) {
            return new RunningGameManager(id, loadedBoard, nextTurn);
        }
        return createEndGame(id, ChessGameStatistics.createNotStartGameResult());
    }
}
