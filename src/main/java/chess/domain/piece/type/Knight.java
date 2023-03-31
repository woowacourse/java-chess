package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public class Knight extends JumperPiece {

    // 순서
    // 1. 이동 가능한 방향인지 확인하기(나이트)
    // 2. 경로 찾아 반환하기(1번가짐) (무조건 빈 리스트 반환)

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    protected boolean isMovableMove(final Position start, final Position end) {
        int absx = Math.abs(start.findGapOfColumn(end));
        int absy = Math.abs(start.findGapOfRank(end));

        return (absx == 1 && absy ==2) || (absx ==2 && absy ==1);
    }

}
