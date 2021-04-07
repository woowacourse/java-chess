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

    public GameResponseDto findGameByGameId(final Long gameID) {
        return chessDao.findGameByGameId(gameID);
    }

    public ScoreResponseDto findScoreByGameId(final Long gameID) {
        return chessDao.findScoreByGameId(gameID);
    }

    public StateResponseDto findStateByGameId(final Long gameID) {
        return chessDao.findStateByGameId(gameID);
    }

    public PathResponseDto movablePath(final MovablePathRequestDto movablePathRequestDto, final Long gameId) {
        ChessManager chessManager = createChessManager(gameId);
        Path path = chessManager.movablePath(Position.of(movablePathRequestDto.getSource()));
        return PathResponseDto.toPath(path);
    }

    public void move(final MoveRequestDto moveRequestDto, final Long gameId) {
        ChessManager chessManager = createChessManager(gameId);
        Piece sourcePiece = chessManager.pickPiece(Position.of(moveRequestDto.getSource()));
        chessManager.move(Position.of(moveRequestDto.getSource()), Position.of(moveRequestDto.getTarget()));
        chessDao.updateScore(chessManager.gameStatus(), gameId);
        chessDao.updateState(chessManager, gameId);
        chessDao.updateTargetPiece(moveRequestDto.getTarget(), sourcePiece, gameId);
        chessDao.updateSourcePiece(moveRequestDto.getSource(), gameId);
        String moveCommand = String.format(MOVE_COMMAND_FORMAT, moveRequestDto.getSource(), moveRequestDto.getTarget());
        chessDao.createHistory(chessManager, moveCommand, gameId);
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
