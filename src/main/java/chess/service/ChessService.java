package chess.service;

import chess.controller.dto.*;
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

    public Long newGame(final NewGameRequestDto newGameRequestDto) {
        ChessManager chessManager = new ChessManager();
        Map<Position, Piece> pieces = chessManager.boardToMap();
        Long gameId = chessDao.createNewGame(newGameRequestDto);
        chessDao.createState(chessManager, gameId);
        chessDao.createScore(chessManager.gameStatus(), gameId);
        createAllPiece(pieces, gameId);
        return gameId;
    }

    private void createAllPiece(final Map<Position, Piece> pieces, final Long gameId) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            chessDao.createPieces(gameId, entry.getKey().parseString(), entry.getValue().getSymbol());
        }
    }

    public List<PieceResponseDto> findPiecesBygameId(final Long gameId) {
        return chessDao.findPiecesBygameId(gameId);
    }

    public GameResponseDto findGameBygameId(final Long gameId) {
        return chessDao.findGameBygameId(gameId);
    }

    public ScoreResponseDto findScoreBygameId(final Long gameId) {
        return chessDao.findScoreBygameId(gameId);
    }

    public StateResponseDto findStateBygameId(final Long gameId) {
        return chessDao.findStateBygameId(gameId);
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
        chessDao.createHistory(historyResponseDto, moveCommand, gameId);
        return historyResponseDto;
    }

    private ChessManager createChessManager(final Long gameId) {
        List<PieceResponseDto> pieceResponseDtos = chessDao.findPiecesBygameId(gameId);
        StateResponseDto stateResponseDto = chessDao.findStateBygameId(gameId);
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

    public List<HistoryResponseDto> findHistoryBygameId(Long gameId) {
        return chessDao.findHistoryBygameId(gameId);
    }
}
