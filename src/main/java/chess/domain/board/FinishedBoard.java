package chess.domain.board;

import chess.domain.UserInterface;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class FinishedBoard extends InProgressBoard {
    private FinishedBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        super(pieces, userInterface);
    }
}
