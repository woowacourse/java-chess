package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessResult;
import chess.domain.Position;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;
import chess.util.MessagePositionConverter;
import chess.view.BoardDto;
import chess.view.OutputView;

import java.util.List;

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
        List<Position> positions = MessagePositionConverter.convert(message);
        Position currentPosition = positions.get(0);
        Position targetPosition = positions.get(1);
        chessGame.move(currentPosition, targetPosition);

        OutputView.printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));

        return chessStatus();
    }

    private GameStatus chessStatus() {
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
