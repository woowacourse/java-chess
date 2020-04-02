package chess.domain.board;

import chess.domain.UserInterface;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class InProgressBoard extends InitializedBoard {
    InProgressBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        super(pieces, userInterface);
    }
}
