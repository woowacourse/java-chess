package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessGameImpl;
import chess.domain.GameResult;
import chess.domain.Position;
import chess.util.MessagePositionConverter;
import chess.view.OutputView;
import chess.view.dto.BoardDto;

public final class ChessAction {

    private ChessGame chessGame;

    public ChessAction(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public GameStatus start() {
        chessGame = ChessGameImpl.initialGame();

        OutputView
            .printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));

        return GameStatus.RUN;
    }

    public GameStatus end() {
        OutputView.printEndGame();
        return GameStatus.EXIT;
    }

    public GameStatus move(String message) {
        MessagePositionConverter messagePositionConverter = new MessagePositionConverter(message);
        Position currentPosition = messagePositionConverter.currentPosition();
        Position targetPosition = messagePositionConverter.targetPosition();
        chessGame.movePiece(currentPosition, targetPosition);

        OutputView
            .printBoard(new BoardDto(chessGame.nameGroupingByPosition(), chessGame.boardSize()));

        return checkGameStatus();
    }

    private GameStatus checkGameStatus() {
        if (chessGame.isCheckmate()) {
            OutputView.printCheckmate(chessGame.currentColor().reverse());
            return GameStatus.EXIT;
        }

        if (chessGame.isKingDead()) {
            OutputView.printKingDead(chessGame.currentColor().reverse());
            return GameStatus.EXIT;
        }

        if (chessGame.isChecked()) {
            OutputView.printCheck();
        }
        return GameStatus.RUN;
    }

    public GameStatus status() {
        GameResult gameResult = chessGame.gameResult();
        OutputView.printResult(gameResult);
        return GameStatus.EXIT;
    }
}
