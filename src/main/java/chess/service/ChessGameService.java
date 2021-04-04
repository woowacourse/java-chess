package chess.service;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.dao.BoardDao;
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
    private ChessGame chessGame;

    public ChessGameService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public BoardDto start() throws SQLException {
        pieceDao.deleteAll();
        boardDao.deleteAll();
        chessGame = new ChessGame();
        int boardId = boardDao.addBoard(chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead());
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
                chessGame.isKingDead());
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

    public BoardDto continueGame() throws SQLException {
        List<PieceDto> piecesDto = pieceDao.selectAllPiece();
        return boardDao.selectBoard(piecesDto);
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
