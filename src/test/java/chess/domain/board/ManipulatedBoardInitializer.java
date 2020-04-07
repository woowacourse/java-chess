package chess.domain.board;

import chess.domain.piece.PieceState;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class ManipulatedBoardInitializer implements BoardInitializer {

    @Override
    public Map<Position, PieceState> create() {
        Map<Position, PieceState> initialBoard = new HashMap<>();
        DefaultBoardPiece piece = DefaultBoardPiece.BLACK_ROOK_1;
        initialBoard.put(piece.getPosition(), piece.getInitialPiece());
        return initialBoard;
    }
}
