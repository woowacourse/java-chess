package chess.domain.piece;

import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    // 얘한테 이 체스말들의 점수 계산해달라고 요청 보내기 (GameResult 에 필요한 도메인)

}
