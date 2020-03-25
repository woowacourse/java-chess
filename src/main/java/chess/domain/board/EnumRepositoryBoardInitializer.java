package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class EnumRepositoryBoardInitializer implements BoardInitializer {

    @Override
    public Map<Position, Piece> create() {
        Map<Position, Piece> initialBoard = new HashMap<>();
        for (InitialPieceRepository pieceRepository : InitialPieceRepository.values()) {
            initialBoard.put(pieceRepository.getPosition(), pieceRepository.getInitialPiece());
        }
        return initialBoard;
    }
}
