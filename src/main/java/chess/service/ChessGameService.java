package chess.service;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.dao.BoardDao;
import chess.dao.MoveLogDao;
import chess.dao.PieceDao;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessResult;
import chess.util.StringPositionConverter;

import java.sql.SQLException;
import java.util.List;

public class ChessGameService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;
    private final MoveLogDao moveLogDao;

    public ChessGameService(BoardDao boardDao, PieceDao pieceDao, MoveLogDao moveLogDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
        this.moveLogDao = moveLogDao;
    }

    public BoardDto start() throws SQLException {
        pieceDao.deleteAll();
        boardDao.deleteAll();
        chessGame = new ChessGame();
        int boardId = boardDao.addBoard(chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead()
        );
        List<PieceDto> pieces = chessGame.getPieces();
        for (PieceDto piece : pieces) {
            pieceDao.addPiece(piece.getName(),
                    piece.getTeamColor(),
                    piece.getScore(),
                    piece.getCurrentPosition(),
                    boardId);
        }
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead(),
                boardName);
    }

    public BoardDto move(String boardName, String source, String target) {
        ChessGame chessGame = new ChessGame();
        executeMoveLog(boardName, chessGame);

        Position currentPosition = StringPositionConverter.convertToPosition(source);
        Position targetPosition = StringPositionConverter.convertToPosition(target);
        chessGame.move(currentPosition, targetPosition);

        moveLogDao.addMoveLog(boardName, source, target);
        pieceDao.update(boardName, source, target);
        boardDao.updateTurn(boardName, chessGame.getCurrentColor());
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead(),
                boardName);
    }

    public BoardDto move(String source, String target) throws SQLException {
        Position currentPosition = StringPositionConverter.convertToPosition(source);
        Position targetPosition = StringPositionConverter.convertToPosition(target);
        chessGame.move(currentPosition, targetPosition);
        pieceDao.updatePiece(source, target);
        boardDao.updateTurn(chessGame.getCurrentColor());
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead());
    }

    public BoardDto status(String boardName) {
        ChessGame chessGame = new ChessGame();
        executeMoveLog(boardName, chessGame);
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead(),
                boardName);
    }

    public ChessResult result(){
        return chessGame.result();
    }

    public String winner(double whiteScore, double blackScore){
        TeamColor winner = TeamColor.BLACK;
        if (whiteScore > blackScore) {
            winner = TeamColor.WHITE;
        }
        return winner.toString();
    }

    public BoardDto status(){
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead());
    }
}
