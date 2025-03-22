package chess.piece;

import chess.position.Position;

public interface 장애물체크해야하는Piece extends Piece {

    boolean 경로상_장애물_확인(final Position 내위치, final Position 가는위치);
}
