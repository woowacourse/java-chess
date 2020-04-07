package chess.domain.board;

import chess.domain.piece.PieceState;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class DefaultBoardInitializer implements BoardInitializer {

    @Override
    public Map<Position, PieceState> create() {
        Map<Position, PieceState> initialBoard = new HashMap<>();
        for (DefaultBoard pieceRepository : DefaultBoard.values()) {
            initialBoard.put(pieceRepository.getPosition(), pieceRepository.getInitialPiece());
        }
        return initialBoard;
    }
}
