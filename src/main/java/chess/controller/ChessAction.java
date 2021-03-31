package chess.controller;

import chess.domain.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessResult;
import chess.domain.game.ImpossibleMoveException;
import chess.domain.game.PieceNotFoundException;
import chess.util.MessagePositionConverter;
import chess.controller.dto.BoardDto;
import chess.view.OutputView;

import java.util.List;

public class ChessAction {

    private static ChessAction instance;
    private ChessGame chessGame;

    private ChessAction() {
    }

    public static ChessAction getInstance() {
        if (instance == null) {
            instance = new ChessAction();
        }
        return instance;
    }

    public GameStatus start() {
        chessGame = new ChessGame();
        OutputView.printBoard(new BoardDto(chessGame.getPieces(), chessGame.getBoardSize(), chessGame.getCurrentColor()));
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

        OutputView.printBoard(new BoardDto(chessGame.getPieces(), chessGame.getBoardSize(), chessGame.getCurrentColor()));

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
