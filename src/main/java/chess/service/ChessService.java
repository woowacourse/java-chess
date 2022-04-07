package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.request.CreatePieceDto;
import chess.dto.request.DeletePieceDto;
import chess.dto.request.UpdatePiecePositionDto;
import chess.dto.response.BoardDto;
import chess.dto.response.GameDto;
import java.util.Map.Entry;

public class ChessService {
    private final GameDao gameDao;
    private final BoardDao boardDao;

    private ChessService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
    }

    public static ChessService of(GameDao gameDao, BoardDao boardDao) {
        return new ChessService(gameDao, boardDao);
    }

    public void initializeGame(String gameId) {
        gameDao.deleteGame(gameId);
        createGame(gameId);
    }

    public void createGame(String gameId) {
        gameDao.createGame(gameId);

        Board initializedBoard = Board.createInitializedBoard();
        for (Entry<Position, Piece> entry : initializedBoard.getValue().entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();

            CreatePieceDto createPieceDto = CreatePieceDto.of(gameId, position, piece);
            boardDao.createPiece(createPieceDto);
        }
    }

    public GameDto getGame(String gameId) {
        GameDto gameDto = gameDao.getGame(gameId);
        BoardDto boardDto = boardDao.getBoard(gameId);
        gameDto.setBoardDto(boardDto);

        return gameDto;
    }

    public BoardDto getBoard(String gameId) {
        return boardDao.getBoard(gameId);
    }

    public void movePiece(UpdatePiecePositionDto updatePiecePositionDto) {
        String gameId = updatePiecePositionDto.getGameId();
        ChessGame chessGame = getGame(gameId).toChessGame();
        chessGame.movePiece(updatePiecePositionDto.getFrom(), updatePiecePositionDto.getTo());

        updateGameTurn(gameId, chessGame);

        boardDao.deletePiece(DeletePieceDto.of(gameId, updatePiecePositionDto.getTo().getXAxis(),
                updatePiecePositionDto.getTo().getYAxis()));
        boardDao.updatePiecePosition(updatePiecePositionDto);
    }

    private void updateGameTurn(String gameId, ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            gameDao.updateTurnToWhite(gameId);
            return;
        }

        gameDao.updateTurnToBlack(gameId);
    }

}
