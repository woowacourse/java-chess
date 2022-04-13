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
import chess.dto.response.ChessGameDto;
import chess.dto.response.PieceColorDto;
import chess.dto.response.ScoreResultDto;
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

    public void movePiece(UpdatePiecePositionDto updatePiecePositionDto) {
        String gameId = updatePiecePositionDto.getGameId();

        ChessGame chessGame = generateChessGame(gameId);
        chessGame.movePiece(updatePiecePositionDto.getFrom(), updatePiecePositionDto.getTo());

        updateGameTurn(gameId, chessGame);
        updatePiecePosition(updatePiecePositionDto, gameId);
    }

    private void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto, String gameId) {
        boardDao.deletePiece(DeletePieceDto.of(gameId, updatePiecePositionDto.getTo()));
        boardDao.updatePiecePosition(updatePiecePositionDto);
    }

    private void updateGameTurn(String gameId, ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            gameDao.updateTurnToWhite(gameId);
            return;
        }

        gameDao.updateTurnToBlack(gameId);
    }

    public PieceColorDto getCurrentTurn(String gameId) {
        return PieceColorDto.from(generateChessGame(gameId));
    }

    public ScoreResultDto getScore(String gameId) {
        return ScoreResultDto.from(generateChessGame(gameId));
    }

    public PieceColorDto getWinColor(String gameId) {
        ChessGame chessGame = generateChessGame(gameId);
        return PieceColorDto.from(chessGame.getWinColor());
    }

    private ChessGame generateChessGame(String gameId) {
        BoardDto boardDto = boardDao.getBoard(gameId);
        ChessGameDto chessGameDto = gameDao.getGame(gameId);

        return ChessGame.of(boardDto.toBoard(), chessGameDto.getCurrentTurnAsPieceColor());
    }

    public BoardDto getBoard(String gameId) {
        return boardDao.getBoard(gameId);
    }

    @Override
    public String toString() {
        return "ChessService{" +
                "gameDao=" + gameDao +
                ", boardDao=" + boardDao +
                '}';
    }
}
