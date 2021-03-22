package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessResult;
import chess.domain.Position;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;
import chess.util.MessagePositionConverter;
import chess.view.BoardDto;
import chess.view.OutputView;

public class ChessAction {

    private ChessGame chessGame;

    public GameStatus start() {
        chessGame = new ChessGame();
        OutputView.printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));
        return GameStatus.RUN;
    }

    public GameStatus end() {
        return GameStatus.EXIT;
    }

    public GameStatus move(String message) throws PieceNotFoundException, ImpossibleMoveException {
        MessagePositionConverter messagePositionConverter = new MessagePositionConverter(message);
        Position currentPosition = messagePositionConverter.currentPosition();
        Position targetPosition = messagePositionConverter.targetPosition();
        chessGame.move(currentPosition, targetPosition);

        OutputView.printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));

        return chessState();
    }

    private GameStatus chessState() {
        if (chessGame.isKingDead()) {
            OutputView.printWinner(chessGame.enemyColor());
            return GameStatus.EXIT;
        }
        if (chessGame.checked()) {
            OutputView.printCheck();
        }
        return GameStatus.RUN;
    }

    public GameStatus status() {
        ChessResult chessResult = chessGame.result();
        OutputView.printResult(chessResult);
        return GameStatus.EXIT;
    }
}
