package chess.domain;

import chess.controller.GameStatus;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(Camp firstCamp, CampSwitcher campSwitcher) {
        this.gameStatus = GameStatus.READY;
        this.chessBoard = new ChessBoard(firstCamp, campSwitcher);
    }

    public void start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.RUNNING;
    }

    public void move(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
    }

    public void end(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.READY;
    }

    public Map<Position, Piece> readBoard(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        return chessBoard.piecesByPosition();
    }

}
