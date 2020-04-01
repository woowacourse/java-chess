package chess.service;

import chess.manager.ChessManager;
import chess.repository.ChessRepository;

public class ChessService {
    private final ChessRepository chessRepository;

    public ChessService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public Long save(ChessManager chessManager) {
        return chessRepository.save(chessManager);
    }


}
