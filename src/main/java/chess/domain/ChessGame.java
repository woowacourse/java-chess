package chess.domain;

import chess.controller.GameStatus;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus = GameStatus.READY;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    public void start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.RUNNING;
    }

    public void move(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        boolean isOver = chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
        if (isOver) {
            gameStatus = GameStatus.OVER;
        }
    }

    public void end(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.READY;
    }

    public boolean isOver() {
        return gameStatus == GameStatus.OVER;
    }

    public GameResultResponse computeResult(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        return new GameResultResponse(
                chessBoard.calculateScoreByCamp(Camp.WHITE),
                chessBoard.calculateScoreByCamp(Camp.BLACK),
                chessBoard.currentTurn().name()
        );
    }

    public Map<Position, Piece> readBoard() {
        return chessBoard.piecesByPosition();
    }

}
