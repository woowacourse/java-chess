package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

/**
 * PieceDto는 체스 데이터를 json으로 가공하여 전송할 때 사용된다.
 * Piece가 가지는 요소 중 화면 출력에 필요한 값(피스의 타입 및 컬러)만 맴버로 가지고 있다.
 * web으로 보낼 때 json으로 가공되기 때문에, 별도의 getter, setter는 존재하지 않는다.
 */
public class PieceDto {
    private final PieceType pieceType;
    private final Color color;

    public PieceDto(final Piece piece) {
        this.pieceType = piece.getPieceType();
        this.color = piece.getColor();
    }
}
