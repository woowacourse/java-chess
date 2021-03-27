package chess.controller;

import chess.controller.converter.MessagePositionConverter;
import chess.domain.ChessGame;
import chess.domain.ChessGameImpl;
import chess.domain.GameResult;
import chess.domain.Positions;

public final class ChessAction {

    private ChessGame chessGame;

    public ChessAction(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public GameStatus start() {
        chessGame = ChessGameImpl.initialGame();

//        OutputView.printBoard(new BoardDto(chessGame.pieceGroupingByPosition(), chessGame.boardSize()));

        return GameStatus.RUN;
    }

    public GameStatus end() {
//        OutputView.printEndGame();
        return GameStatus.EXIT;
    }

    public GameStatus move(String message) {
        Positions positions = MessagePositionConverter.convert(message);
        chessGame.movePiece(positions.currentPosition(), positions.targetPosition());

//        OutputView.printBoard(new BoardDto(chessGame.pieceGroupingByPosition(), chessGame.boardSize()));

        return checkGameStatus();
    }

    private GameStatus checkGameStatus() {
        if (chessGame.isCheckmate()) {
//            OutputView.printCheckmate(chessGame.currentColor().reverse());
            return GameStatus.EXIT;
        }

        if (chessGame.isKingDead()) {
//            OutputView.printKingDead(chessGame.currentColor().reverse());
            return GameStatus.EXIT;
        }

        if (chessGame.isChecked()) {
//            OutputView.printCheck();
        }
        return GameStatus.RUN;
    }

    public GameStatus status() {
        GameResult gameResult = chessGame.gameResult();
//        OutputView.printResult(gameResult);
        return GameStatus.EXIT;
    }
}
