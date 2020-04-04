package chess.service;

import chess.board.BoardGenerator;
import chess.manager.ChessManager;
import chess.piece.Piece;
import chess.repository.ChessRepository;
import chess.repository.MovementRepository;
import chess.repository.entity.ChessEntity;
import chess.repository.entity.Movement;
import chess.web.dto.MoveRequest;
import chess.web.dto.MoveResponse;
import chess.web.dto.SaveResponse;

import java.util.List;
import java.util.NoSuchElementException;

public class ChessService {
    private final ChessRepository chessRepository;
    private final MovementRepository movementRepository;

    public ChessService(ChessRepository chessRepository, MovementRepository movementRepository) {
        this.chessRepository = chessRepository;
        this.movementRepository = movementRepository;
    }

    public SaveResponse save() {
        ChessEntity chessEntity = new ChessEntity(true);
        chessEntity = chessRepository.save(chessEntity);

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        return new SaveResponse(chessEntity.getId(), chessManager);
    }

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
}
