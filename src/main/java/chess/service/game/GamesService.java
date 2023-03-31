package chess.service.game;

import chess.repository.chess.ChessGameRepository;
import java.util.List;

public class GamesService {

    private final ChessGameRepository chessGameRepository;

    public GamesService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public List<Integer> findAllGamesByUserId(int userId) {
        return chessGameRepository.findAllIdsByUserId(userId);
    }
}
