package chess.service;

import chess.controller.web.dto.game.GameResponseDto;
import chess.controller.web.dto.history.HistoryResponseDto;
import chess.controller.web.dto.move.PathResponseDto;
import chess.controller.web.dto.piece.PieceResponseDto;
import chess.controller.web.dto.piece.PieceResponseDtos;
import chess.controller.web.dto.score.ScoreResponseDto;
import chess.controller.web.dto.state.StateResponseDto;
import chess.dao.*;
import chess.domain.History;
import chess.domain.MoveCommand;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.game.Game;
import chess.domain.manager.ChessManager;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;

import java.util.List;

public class ChessService {

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
        Long gameId = gameDao.saveGame(game);
        stateDao.saveState(chessManager, gameId);
        scoreDao.saveScore(chessManager.gameStatus(), gameId);
        pieceDao.savePieces(gameId, chessManager.boardToMap());
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

    public PathResponseDto movablePath(final String source, final Long gameId) {
        ChessManager chessManager = loadChessManager(gameId);
        Path path = chessManager.movablePath(Position.of(source));
        return PathResponseDto.from(path);
    }

    public HistoryResponseDto move(final MoveCommand moveCommand, final Long gameId) {
        ChessManager chessManager = loadChessManager(gameId);
        History history = History.of(moveCommand, chessManager);
        Piece sourcePiece = chessManager.pickPiece(Position.of(moveCommand.source()));
        chessManager.move(Position.of(moveCommand.source()), Position.of(moveCommand.target()));
        scoreDao.updateScore(chessManager.gameStatus(), gameId);
        stateDao.updateState(chessManager, gameId);
        this.updatePieceByMove(moveCommand, sourcePiece, gameId);
        historyDao.saveHistory(history, gameId);
        return HistoryResponseDto.from(history);
    }

    private void updatePieceByMove(MoveCommand moveCommand, Piece sourcePiece, Long gameId) {
        pieceDao.updateTargetPiece(moveCommand.target(), sourcePiece, gameId);
        pieceDao.updateSourcePiece(moveCommand.source(), gameId);
    }

    private ChessManager loadChessManager(final Long gameId) {
        PieceResponseDtos pieceResponseDtos = new PieceResponseDtos(pieceDao.findPiecesByGameId(gameId));
        StateResponseDto stateResponseDto = stateDao.findStateByGameId(gameId);
        return new ChessManager(
                pieceResponseDtos.toBoard(),
                Owner.valueOf(stateResponseDto.getTurnOwner()),
                stateResponseDto.getTurnNumber(),
                stateResponseDto.isPlaying());
    }
}
