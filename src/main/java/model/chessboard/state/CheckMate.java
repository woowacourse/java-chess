package model.chessboard.state;

import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.position.Position;

public final class CheckMate extends DefaultState {
    CheckMate(Map<Position, PieceHolder> chessBoard, Color currentColor) {
        super(chessBoard, currentColor);
    }

    @Override
    public DefaultState nextState() {
        throw new IllegalStateException("체크메이트로 인해 게임이 종료되었으므로 다음 상태가 존재하지 않습니다.");
    }

    @Override
    public DefaultState move(Position source, Position destination) {
        throw new IllegalStateException("체크메이트로 인해 게임이 종료되었으므로 이동할 수 없습니다.");
    }

    @Override
    protected boolean isCheckedBy(Color currentColor) {
        throw new IllegalStateException("체크메이트로 인해 게임이 종료되었으므로 체크를 확인할 수 없습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
