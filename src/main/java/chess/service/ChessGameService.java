package chess.service;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.dao.BoardDao;
import chess.dao.MoveLogDao;
import chess.dao.PieceDao;
import chess.domain.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessResult;
import chess.util.StringPositionConverter;

import java.util.List;
import java.util.Map;

public class ChessGameService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;
    private final MoveLogDao moveLogDao;

    public ChessGameService(BoardDao boardDao, PieceDao pieceDao, MoveLogDao moveLogDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
        this.moveLogDao = moveLogDao;
    }

    public BoardDto start(String boardName) {
        resetBoard(boardName);

        ChessGame chessGame = new ChessGame();
        boardDao.add(boardName,
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead()
        );
        List<PieceDto> pieces = chessGame.getPieces();
        pieceDao.addAll(boardName, pieces);

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

    public BoardDto continueGame(String boardName) {
        ChessGame chessGame = new ChessGame();
        executeMoveLog(boardName, chessGame);
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead(),
                boardName);
    }

    public ChessResult result(String boardName) {
        ChessGame chessGame = new ChessGame();
        executeMoveLog(boardName, chessGame);
        return chessGame.result();
    }

    public BoardDto resultBoard(String boardName) {
        ChessGame chessGame = new ChessGame();
        executeMoveLog(boardName, chessGame);
        return new BoardDto(chessGame.getPieces(),
                chessGame.getBoardSize(),
                chessGame.getCurrentColor(),
                chessGame.checked(),
                chessGame.isKingDead(),
                boardName);
    }

    private void resetBoard(String boardName) {
        try {
            boardDao.deleteByName(boardName);
            pieceDao.deleteByBoardName(boardName);
            moveLogDao.deleteByBoardName(boardName);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    private void executeMoveLog(String boardName, ChessGame chessGame) {
        try {
            Map<Position, Position> moveLog = moveLogDao.selectByBoardName(boardName);
            moveLog.forEach(chessGame::move);
        } catch (IllegalArgumentException e) {
            return;
        }
    }
}
