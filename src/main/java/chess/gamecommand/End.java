package chess.gamecommand;

import java.util.List;

import chess.board.Position;
import chess.piece.Piece;

public class End implements CommandStatus {

    @Override
    public CommandStatus start() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 시작할 수 없습니다.");
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 기물을 움직일 수 없습니다.");
    }


    @Override
    public CommandStatus end() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 종료할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public List<Piece> getPieces() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 기물들을 반환할 수 없습니다.");
    }
}
