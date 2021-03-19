package chess.controller;

import chess.domain.*;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;
import chess.util.MessagePositionConverter;
import chess.view.BoardDto;
import chess.view.OutputView;

public class ChessAction {

    private ChessGame chessGame;

    public GameStatus start(String message) {
        chessGame = new ChessGame();
        OutputView.printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));
        return GameStatus.RUN;
    }

    public GameStatus end(String message) {
        return GameStatus.EXIT;
    }

    public GameStatus move(String message) throws PieceNotFoundException, ImpossibleMoveException {
        MessagePositionConverter messagePositionConverter = new MessagePositionConverter(message);
        Position currentPosition = messagePositionConverter.currentPosition();
        Position targetPosition = messagePositionConverter.targetPosition();
        chessGame.move(currentPosition, targetPosition);
        OutputView.printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));
        if (chessGame.isKingDead()) {
            OutputView.printWinner(chessGame.enemyColor());
            return GameStatus.EXIT;
        }
        return GameStatus.RUN;
    }

    public GameStatus status(String message) {
        Score whiteTeamScore = chessGame.totalScoreByTeamColor(TeamColor.WHITE);
        Score blackTeamScore = chessGame.totalScoreByTeamColor(TeamColor.BLACK);
        OutputView.printScore(whiteTeamScore, blackTeamScore);
        ChessResult chessResult = new ChessResult(whiteTeamScore, blackTeamScore);
        OutputView.printWinner(chessResult.winner());
        return GameStatus.EXIT;
    }
}
