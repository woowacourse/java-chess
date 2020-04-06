package chess.service;

import chess.board.BoardGenerator;
import chess.board.ChessBoard;
import chess.board.ChessBoardAdapter;
import chess.entity.ChessEntity;
import chess.entity.Movement;
import chess.manager.ChessManager;
import chess.piece.Piece;
import chess.repository.ChessRepository;
import chess.repository.MovementRepository;
import chess.web.dto.ChessBoardResponse;
import chess.web.dto.MoveRequest;
import chess.web.dto.MoveResponse;
import chess.web.dto.SavedGameBundleResponse;
import chess.web.dto.TilesDto;

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
        ChessManager chessManager = new ChessManager(BoardGenerator.create());

        restore(moveRequest, chessManager);
        Piece deadPiece = chessManager.move(moveRequest.getSourceKey(), moveRequest.getTargetKey());

        movementRepository.save(moveRequest.toEntity());
        if (deadPiece.isKing()) {
            endGame(moveRequest, chessManager);
        }

        return new MoveResponse(chessManager, moveRequest, deadPiece);
    }

    private void restore(MoveRequest moveRequest, ChessManager chessManager) {
        List<Movement> movements = movementRepository.findAllByChessId(moveRequest.getId());
        chessManager.moveAll(movements);
    }

    private void endGame(MoveRequest moveRequest, ChessManager chessManager) {
        ChessEntity chessEntity = chessRepository.findById(moveRequest.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", moveRequest.getId())));
        chessEntity.endGame(chessManager.getCurrentTeam());
        chessRepository.update(chessEntity);
    }

    public ChessBoardResponse save() {
        ChessEntity chessEntity = new ChessEntity(true);
        chessEntity = chessRepository.save(chessEntity);

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        return new ChessBoardResponse(chessEntity.getId(), chessManager);
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
}
