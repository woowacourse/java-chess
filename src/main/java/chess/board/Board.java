package chess.board;

import chess.piece.LivePiece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final List<LivePiece> pieces;

    public Board(final List<LivePiece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    // NOTE: 생성자 매개변수 없으면 기본적으로 체스말 다 넣음
    public Board() {
        this.pieces = createPieces();
    }

    // NOTE: 하드코딩으로 말 위치 생성해서 넣어주기
    // TODO: 아직 기물 구현 안 되어서 다 완성 안 됨
    public List<LivePiece> createPieces() {
        List<LivePiece> result = new ArrayList<>();
        result.addAll(createWhitePiece());
        result.addAll(createBlackPiece());
        return result;
    }

    // TODO: 실제 기물 구현할 때 마다 하나씩 추가해야 함~
    public List<LivePiece> createWhitePiece() {
        List<LivePiece> result = new ArrayList();

        return result;
    }

    // TODO: 실제 기물 구현할 때 마다 하나씩 추가해야 함~
    public List<LivePiece> createBlackPiece() {
        List<LivePiece> result = new ArrayList();

        return result;
    }

    public List<LivePiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

}
