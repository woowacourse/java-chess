package chess.service;

import chess.board.BoardGenerator;
import chess.board.ChessBoard;
import chess.board.ChessBoardAdapter;
import chess.entity.ChessGame;
import chess.entity.Movement;
import chess.manager.ChessManager;
import chess.piece.Piece;
import chess.piece.Team;
import chess.repository.ChessRepository;
import chess.repository.MovementRepository;
import chess.service.dto.ChessBoardResponse;
import chess.service.dto.MoveRequest;
import chess.service.dto.MoveResponse;
import chess.service.dto.SavedGameBundleResponse;
import chess.service.dto.SurrenderRequest;
import chess.service.dto.TilesDto;

import java.util.List;
import java.util.NoSuchElementException;

public class ChessService {
    private final ChessRepository chessRepository;
    private final MovementRepository movementRepository;

    public ChessService(ChessRepository chessRepository, MovementRepository movementRepository) {
        this.chessRepository = chessRepository;
        this.movementRepository = movementRepository;
    }

    private static final TilesDto EMPTY_BOARD = new TilesDto(new ChessManager(new ChessBoardAdapter(ChessBoard.empty())));

    public MoveResponse move(MoveRequest moveRequest) {
        ChessGame chessGame = chessRepository.findById(moveRequest.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", moveRequest.getId())));

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        restore(moveRequest, chessManager);
        Piece deadPiece = chessManager.move(moveRequest.getSourceKey(), moveRequest.getTargetKey());

        movementRepository.save(moveRequest.toEntity());
        if (deadPiece.isKing()) {
            chessGame.endGame(chessManager.getCurrentTeam());
            chessRepository.update(chessGame);
        }

        return new MoveResponse(chessManager, moveRequest, deadPiece);
    }

    private void restore(MoveRequest moveRequest, ChessManager chessManager) {
        List<Movement> movements = movementRepository.findAllByChessId(moveRequest.getId());
        chessManager.moveAll(movements);
    }

    public ChessBoardResponse save() {
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessRepository.save(chessGame);

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        return new ChessBoardResponse(chessGame.getId(), chessManager);
    }

    public SavedGameBundleResponse loadAllSavedGames() {
        return new SavedGameBundleResponse(chessRepository.findAll());
    }

    public TilesDto getEmptyBoard() {
        return EMPTY_BOARD;
    }

    public ChessBoardResponse loadSavedGame(Long targetId) {
        chessRepository.findById(targetId)
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", targetId)));

        List<Movement> movements = movementRepository.findAllByChessId(targetId);
        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        chessManager.moveAll(movements);

        return new ChessBoardResponse(targetId, chessManager);
    }

    public void surrender(SurrenderRequest surrenderRequest) {
        ChessGame chessGame = chessRepository.findById(surrenderRequest.getGameId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", surrenderRequest.getGameId())));

        Team winTeam = Team.valueOf(surrenderRequest.getLoseTeam()).getOppositeTeam();
        chessGame.endGame(winTeam);

        chessRepository.update(chessGame);
    }
}
