package chess.domain;

import chess.controller.GameStatus;
import chess.dao.ChessBoardDao;
import chess.dao.InMemoryChessBoardDao;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import java.util.Map;

public class ChessGame {

    private final ChessBoardDao chessBoardDao = new InMemoryChessBoardDao();
    private ChessBoard chessBoard;
    private GameStatus gameStatus = GameStatus.READY;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    // TODO 게임방 id 입력받기
    public void start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.RUNNING;
        // TODO ChessGameDao : currentTurn, isOver 확인
        chessBoard = chessBoardDao.find()
                .orElse(new ChessBoard());
    }

    public void move(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        boolean isOver = chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
        // TODO ChessGameDao : currentTurn, isOver 갱신
        if (isOver) {
            gameStatus = GameStatus.OVER;
        }
        chessBoardDao.delete();
        chessBoardDao.insert(chessBoard);
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
