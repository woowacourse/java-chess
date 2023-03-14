package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Pawn extends Piece {

    public Pawn(final Position position, final Side side) {
        super(position, side);
    }

    public void move(final int fileMovingCount, final int rankMovingCount) {
        position.move(fileMovingCount, rankMovingCount);
    }

    public File getFile() {
        return position.getFile();
    }

    public Rank getRank() {
        return position.getRank();
    }

    // TODO: 2023/03/14 시작 위치 체크

    // TODO: 2023/03/14 타겟위치로 이동 가능한지 체크
}
