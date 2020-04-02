package chess.service;

import chess.manager.ChessManager;
import chess.repository.ChessRepository;
import chess.web.dto.SaveResponse;

public class ChessService {
    private final ChessRepository chessRepository;

    public ChessService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public SaveResponse save(ChessManager chessManager) {
        Long id = chessRepository.save(chessManager);
        return new SaveResponse(id, chessManager);
    }


}
