package chess.service;

import chess.controller.web.dto.move.MovablePathRequestDto;
import chess.controller.web.dto.move.MoveRequestDto;
import chess.controller.web.dto.move.PathResponseDto;
import chess.controller.web.dto.piece.PieceResponseDto;
import chess.controller.web.dto.game.GameResponseDto;
import chess.controller.web.dto.game.GameRequestDto;
import chess.controller.web.dto.history.HistoryResponseDto;
import chess.controller.web.dto.score.ScoreResponseDto;
import chess.controller.web.dto.state.StateResponseDto;
import chess.dao.*;
import chess.domain.board.Board;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.game.Game;
import chess.domain.manager.ChessManager;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.util.PieceConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private static final String MOVE_COMMAND_FORMAT = "move %s %s";

    private final GameDao gameDao;
    private final HistoryDao historyDao;
    private final PieceDao pieceDao;
    private final ScoreDao scoreDao;
    private final StateDao stateDao;

    public ChessService() {
        this.gameDao = new GameDao();
        this.historyDao = new HistoryDao();
        this.pieceDao = new PieceDao();
        this.scoreDao = new ScoreDao();
        this.stateDao = new StateDao();
    }

    public Long saveGame(final Game game) {
        ChessManager chessManager = new ChessManager();
        Map<Position, Piece> pieces = chessManager.boardToMap();
        Long gameId = gameDao.saveGame(game);
        stateDao.saveState(chessManager, gameId);
        scoreDao.saveScore(chessManager.gameStatus(), gameId);
        pieceDao.savePieces(gameId, pieces);
        return gameId;
    }

    public List<PieceResponseDto> findPiecesById(final Long gameId) {
        return pieceDao.findPiecesByGameId(gameId);
    }

    public GameResponseDto findGameByGameId(final Long gameId) {
        return gameDao.findGameById(gameId);
    }

    public ScoreResponseDto findScoreByGameId(final Long gameId) {
        return scoreDao.findScoreByGameId(gameId);
    }

    public StateResponseDto findStateByGameId(final Long gameId) {
        return stateDao.findStateByGameId(gameId);
    }

    public List<HistoryResponseDto> findHistoryByGameId(final Long gameId) {
        return historyDao.findHistoryByGameId(gameId);
    }

    public PathResponseDto movablePath(final MovablePathRequestDto movablePathRequestDto, final Long gameId) {
        ChessManager chessManager = loadChessManager(gameId);
        Path path = chessManager.movablePath(Position.of(movablePathRequestDto.getSource()));
        return PathResponseDto.from(path);
    }

    public HistoryResponseDto move(final MoveRequestDto moveRequestDto, final Long gameId) {
        String moveCommand = String.format(MOVE_COMMAND_FORMAT, moveRequestDto.getSource(), moveRequestDto.getTarget());
        ChessManager chessManager = loadChessManager(gameId);
        HistoryResponseDto historyResponseDto = HistoryResponseDto.from(moveCommand, chessManager);
        Piece sourcePiece = chessManager.pickPiece(Position.of(moveRequestDto.getSource()));
        chessManager.move(Position.of(moveRequestDto.getSource()), Position.of(moveRequestDto.getTarget()));
        scoreDao.updateScore(chessManager.gameStatus(), gameId);
        stateDao.updateState(chessManager, gameId);
        pieceDao.updateTargetPiece(moveRequestDto.getTarget(), sourcePiece, gameId);
        pieceDao.updateSourcePiece(moveRequestDto.getSource(), gameId);
        historyDao.saveHistory(historyResponseDto, gameId);
        return historyResponseDto;
    }

    private ChessManager loadChessManager(final Long gameId) {
        List<PieceResponseDto> pieceResponseDtos = pieceDao.findPiecesByGameId(gameId);
        StateResponseDto stateResponseDto = stateDao.findStateByGameId(gameId);
        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceResponseDto pieceResponseDto : pieceResponseDtos) {
            pieces.put(Position.of(pieceResponseDto.getPosition()), PieceConverter.parsePiece(pieceResponseDto.getSymbol()));
        }
        return new ChessManager(
                new Board(pieces),
                Owner.valueOf(stateResponseDto.getTurnOwner()),
                stateResponseDto.getTurnNumber(),
                stateResponseDto.isPlaying());
    }
}
