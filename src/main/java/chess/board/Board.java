package chess.board;

import chess.Color;
import chess.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.LivePiece;
import chess.piece.Queen;
import chess.piece.Rook;
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
        // 룩 추가
        result.add(new LivePiece(new Position(1, 1), new Rook(Color.WHITE)));
        result.add(new LivePiece(new Position(1, 8), new Rook(Color.WHITE)));

        // 비숍 추가
        result.add(new LivePiece(new Position(1, 3), new Bishop(Color.WHITE)));
        result.add(new LivePiece(new Position(1, 6), new Bishop(Color.WHITE)));

        // 퀸 추가
        result.add(new LivePiece(new Position(1, 4), new Queen(Color.WHITE)));

        // 킹 추가
        result.add(new LivePiece(new Position(1, 5), new King(Color.WHITE)));

        return result;
    }

    // TODO: 실제 기물 구현할 때 마다 하나씩 추가해야 함~
    public List<LivePiece> createBlackPiece() {
        List<LivePiece> result = new ArrayList();
        // 룩 추가
        result.add(new LivePiece(new Position(8, 1), new Rook(Color.BLACK)));
        result.add(new LivePiece(new Position(8, 8), new Rook(Color.BLACK)));

        // 비숍 추가
        result.add(new LivePiece(new Position(8, 3), new Bishop(Color.BLACK)));
        result.add(new LivePiece(new Position(8, 6), new Bishop(Color.BLACK)));

        // 퀸 추가
        result.add(new LivePiece(new Position(8, 4), new Queen(Color.BLACK)));

        // 킹 추가
        result.add(new LivePiece(new Position(8, 5), new King(Color.BLACK)));

        return result;
    }

    public List<LivePiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

}
