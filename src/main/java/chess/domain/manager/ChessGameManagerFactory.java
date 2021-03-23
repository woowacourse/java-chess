package chess.domain.manager;

import chess.domain.board.DefaultBoardInitializer;
import chess.domain.piece.ColoredPieces;
import chess.domain.piece.attribute.Color;
import chess.domain.statistics.ChessGameStatistics;

import java.util.List;
import java.util.stream.Collectors;

public class ChessGameManagerFactory {
    public ChessGameManagerFactory() {
    }

    public static ChessGameManager createRunningGame() {
        List<ColoredPieces> coloredPieces = Color.getUserColors().stream()
                .map(ColoredPieces::createByColor)
                .collect(Collectors.toList());
        return new RunningGameManager(DefaultBoardInitializer.getBoard(), coloredPieces, Color.WHITE);
    }

    public static ChessGameManager createEndGame(ChessGameStatistics chessGameStatistics) {
        return new EndChessGameManager(chessGameStatistics);
    }

    public static ChessGameManager createNotStartedGameManager(){
        return new NotStartedChessGameManager();
    }
}
