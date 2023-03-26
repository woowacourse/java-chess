package chess.domain;

import chess.controller.GameStatus;
import chess.dao.boardpieces.BoardPiecesDao;
import chess.dao.boardpieces.InMemoryBoardPiecesDao;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import java.util.Map;

public class ChessGame {

    private final BoardPiecesDao boardPiecesDao = new InMemoryBoardPiecesDao();
    private ChessBoard chessBoard;
    private GameStatus gameStatus = GameStatus.READY;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    // TODO 게임방 id 입력받기
    public void start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.RUNNING;
        Map<Position, Piece> piecesByPosition = boardPiecesDao.find(1)
                .orElse(PieceInitializer.createPiecesWithPosition());
        // TODO ChessGameDao : currentTurn, isOver 반영
        chessBoard = new ChessBoard(piecesByPosition);
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
        boardPiecesDao.delete(1);
        boardPiecesDao.insert(1, chessBoard.piecesByPosition());
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
