package chess.serviece;

import chess.controller.GameStatus;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardStatus;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final ChessBoardService chessBoardService = new ChessBoardService();
    private ChessBoard chessBoard;
    private GameStatus gameStatus = GameStatus.READY;

    public void validateCommand(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
    }

    public List<Integer> availableBoards() {
        return chessBoardService.findAllBoardIds();
    }

    public void start(int boardId) {
        gameStatus = GameStatus.RUNNING;
        this.chessBoard = chessBoardService.findChessBoardById(boardId);
    }

    public void move(CommandRequest commandRequest) {
        chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
        ChessBoardStatus boardStatus = chessBoard.status();
        if (boardStatus.isOver()) {
            gameStatus = GameStatus.OVER;
        }
        chessBoardService.updateChessBoard(chessBoard);
    }

    public void end(CommandRequest commandRequest) {
        gameStatus = GameStatus.READY;
    }

    public boolean isOver() {
        return gameStatus == GameStatus.OVER;
    }

    public GameResultResponse computeResult(CommandRequest commandRequest) {
        return new GameResultResponse(
                chessBoard.calculateScoreByCamp(Camp.WHITE),
                chessBoard.calculateScoreByCamp(Camp.BLACK),
                chessBoard.status().getCurrentTurn().name()
        );
    }

    public Map<Position, Piece> readBoard() {
        return chessBoard.piecesByPosition();
    }

}
