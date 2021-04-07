package chess.service;

import chess.controller.dto.*;
import chess.dao.ChessDao;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class ChessService {

    private ChessManager chessManager;
    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
    }

    public void move(MoveRequestDto moveRequestDto) {
        chessManager.move(Position.of(moveRequestDto.getSource()), Position.of(moveRequestDto.getTarget()));
    }

    public PathResponseDto movablePath(MovablePathRequestDto movablePathRequestDto) {
        Path path = chessManager.movablePath(Position.of(movablePathRequestDto.getSource()));
        return PathResponseDto.toPath(path);
    }

    public StatusResponseDto gameStatus() {
        return StatusResponseDto.toGameStatus(chessManager.gameStatus());
    }

    public Long newGame(final NewGameRequestDto newGameRequestDto) {
        ChessManager chessManager = new ChessManager();
        Map<Position, Piece> pieces = chessManager.boardToMap();
        Long gameID = chessDao.createNewGame(newGameRequestDto);
        chessDao.createState(chessManager, gameID);
        chessDao.createScore(chessManager.gameStatus(), gameID);
        createAllPiece(pieces, gameID);
        return gameID;
    }

    private void createAllPiece(final Map<Position, Piece> pieces, final Long gameID) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            chessDao.createPieces(gameID, entry.getKey().parseString(), entry.getValue().getSymbol());
        }
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameID) {
        return chessDao.findPiecesByGameId(gameID);
    }

    public StateResponseDto gameState() {
        return StateResponseDto.toChessManager(chessManager);
    }

    public GameResponseDto findGameByGameId(final Long gameID) {
        return chessDao.findGameByGameId(gameID);
    }

    public ScoreResponseDto findScoreByGameId(final Long gameID) {
        return chessDao.findScoreByGameId(gameID);
    }

    public StateResponseDto findStateByGameId(final Long gameID) {
        return chessDao.findStateByGameId(gameID);
    }
}
