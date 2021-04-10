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
import chess.dao.ChessDao;
import chess.domain.board.Board;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.util.PieceConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private static final String MOVE_COMMAND_FORMAT = "move %s %s";

    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
    }

    public Long saveGame(final GameRequestDto gameRequestDto) {
        ChessManager chessManager = new ChessManager();
        Map<Position, Piece> pieces = chessManager.boardToMap();
        Long gameId = chessDao.saveGame(gameRequestDto);
        chessDao.saveState(chessManager, gameId);
        chessDao.saveScore(chessManager.gameStatus(), gameId);
        chessDao.savePieces(gameId, pieces);
        return gameId;
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameId) {
        return chessDao.findPiecesByGameId(gameId);
    }

    public GameResponseDto findGameByGameId(final Long gameId) {
        return chessDao.findGameByGameId(gameId);
    }

    public ScoreResponseDto findScoreByGameId(final Long gameId) {
        return chessDao.findScoreByGameId(gameId);
    }

    public StateResponseDto findStateByGameId(final Long gameId) {
        return chessDao.findStateByGameId(gameId);
    }

    public List<HistoryResponseDto> findHistoryByGameId(final Long gameId) {
        return chessDao.findHistoryByGameId(gameId);
    }

    public PathResponseDto movablePath(final MovablePathRequestDto movablePathRequestDto, final Long gameId) {
        ChessManager chessManager = createChessManager(gameId);
        Path path = chessManager.movablePath(Position.of(movablePathRequestDto.getSource()));
        return PathResponseDto.toPath(path);
    }

    public HistoryResponseDto move(final MoveRequestDto moveRequestDto, final Long gameId) {
        String moveCommand = String.format(MOVE_COMMAND_FORMAT, moveRequestDto.getSource(), moveRequestDto.getTarget());
        ChessManager chessManager = createChessManager(gameId);
        HistoryResponseDto historyResponseDto = HistoryResponseDto.toChessManager(moveCommand, chessManager);
        Piece sourcePiece = chessManager.pickPiece(Position.of(moveRequestDto.getSource()));
        chessManager.move(Position.of(moveRequestDto.getSource()), Position.of(moveRequestDto.getTarget()));
        chessDao.updateScore(chessManager.gameStatus(), gameId);
        chessDao.updateState(chessManager, gameId);
        chessDao.updateTargetPiece(moveRequestDto.getTarget(), sourcePiece, gameId);
        chessDao.updateSourcePiece(moveRequestDto.getSource(), gameId);
        chessDao.createHistory(historyResponseDto, gameId);
        return historyResponseDto;
    }

    private ChessManager createChessManager(final Long gameId) {
        List<PieceResponseDto> pieceResponseDtos = chessDao.findPiecesByGameId(gameId);
        StateResponseDto stateResponseDto = chessDao.findStateByGameId(gameId);
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
