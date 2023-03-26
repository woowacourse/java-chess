package chess.domain;

import chess.controller.GameStatus;
import chess.dao.boardpieces.BoardPiecesDao;
import chess.dao.boardpieces.LocalBoardPiecesDao;
import chess.dao.boardstatuses.BoardStatusesDao;
import chess.dao.boardstatuses.LocalBoardStatusesDao;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardStatus;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import java.util.Map;

public class ChessGame {

    private final BoardPiecesDao boardPiecesDao = new LocalBoardPiecesDao();
    private final BoardStatusesDao boardStatusesDao = new LocalBoardStatusesDao();
    private ChessBoard chessBoard;
    private GameStatus gameStatus = GameStatus.READY;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    // TODO 게임방 id 입력받기
    public void start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        gameStatus = GameStatus.RUNNING;
        // TODO 보드 조회 or 생성 로직 정리하기
        Map<Position, Piece> piecesByPosition = boardPiecesDao.find(1)
                .orElse(PieceInitializer.createPiecesWithPosition());
        ChessBoardStatus status = boardStatusesDao.find(1)
                .orElse(new ChessBoardStatus(Camp.WHITE, false));
        chessBoard = new ChessBoard(piecesByPosition, status);
    }

    public void move(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        chessBoard.move(
                Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate())
        );
        ChessBoardStatus boardStatus = chessBoard.status();
        if (boardStatus.isOver()) {
            gameStatus = GameStatus.OVER;
        }
        boardPiecesDao.insertOrUpdate(1, chessBoard.piecesByPosition());
        boardStatusesDao.insertOrUpdate(1, boardStatus);
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
                chessBoard.status().getCurrentTurn().name()
        );
    }

    public Map<Position, Piece> readBoard() {
        return chessBoard.piecesByPosition();
    }

}
