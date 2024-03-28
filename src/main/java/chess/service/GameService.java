package chess.service;

import chess.domain.square.Movement;
import chess.dto.SquareRequest;
import chess.repository.MovementRepository;
import java.util.List;

public class GameService {

    private final MovementRepository movementRepository;

    public GameService(final MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public List<Movement> findMoves(final long roomId) {
        return movementRepository.findAllByRoomId(roomId);
    }

    public void createMove(final long roomId, final String source, final String target) {
        movementRepository.save(roomId, Movement.of(SquareRequest.from(source), SquareRequest.from(target)));
    }
}
