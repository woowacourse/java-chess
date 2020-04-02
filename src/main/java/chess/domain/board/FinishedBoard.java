package chess.domain.board;

import chess.domain.ui.UserInterface;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

import java.util.Map;

public class FinishedBoard extends RunningBoard {
    private FinishedBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        super(pieces, userInterface);
    }

    @Override
    public Board movePiece() {
        throw new IllegalStateException("게임이 종료되어 체스말을 움직일 수 없습니다.");
    }
}
