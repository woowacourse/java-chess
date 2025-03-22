package chess.board;

import chess.Color;
import chess.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.LivePiece;
import chess.piece.Pawn;
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
    public List<LivePiece> createPieces() {
        List<LivePiece> result = new ArrayList<>();
        result.addAll(createWhitePiece());
        result.addAll(createBlackPiece());
        return result;
    }

    // NOTE: 초기 기물 위치 추가 다 함
    public List<LivePiece> createWhitePiece() {
        List<LivePiece> result = new ArrayList();
        // 룩 추가
        result.add(new LivePiece(new Position(1, 1), new Rook(Color.WHITE)));
        result.add(new LivePiece(new Position(1, 8), new Rook(Color.WHITE)));

        // 나이트 추가
        result.add(new LivePiece(new Position(1, 2), new Knight(Color.WHITE)));
        result.add(new LivePiece(new Position(1, 7), new Knight(Color.WHITE)));

        // 비숍 추가
        result.add(new LivePiece(new Position(1, 3), new Bishop(Color.WHITE)));
        result.add(new LivePiece(new Position(1, 6), new Bishop(Color.WHITE)));

        // 퀸 추가
        result.add(new LivePiece(new Position(1, 4), new Queen(Color.WHITE)));

        // 킹 추가
        result.add(new LivePiece(new Position(1, 5), new King(Color.WHITE)));

        // 폰 추가
        for (int j = 1; j <= 8; ++j) {
            result.add(new LivePiece(new Position(2, j), new Pawn(Color.WHITE)));
        }

        return result;
    }

    // NOTE: 초기 기물 위치 추가 다 함
    public List<LivePiece> createBlackPiece() {
        List<LivePiece> result = new ArrayList();
        // 룩 추가
        result.add(new LivePiece(new Position(8, 1), new Rook(Color.BLACK)));
        result.add(new LivePiece(new Position(8, 8), new Rook(Color.BLACK)));

        // 나이트 추가
        result.add(new LivePiece(new Position(8, 2), new Knight(Color.BLACK)));
        result.add(new LivePiece(new Position(8, 7), new Knight(Color.BLACK)));

        // 비숍 추가
        result.add(new LivePiece(new Position(8, 3), new Bishop(Color.BLACK)));
        result.add(new LivePiece(new Position(8, 6), new Bishop(Color.BLACK)));

        // 퀸 추가
        result.add(new LivePiece(new Position(8, 4), new Queen(Color.BLACK)));

        // 킹 추가
        result.add(new LivePiece(new Position(8, 5), new King(Color.BLACK)));

        // 폰 추가
        for (int j = 1; j <= 8; ++j) {
            result.add(new LivePiece(new Position(7, j), new Pawn(Color.BLACK)));
        }
        return result;
    }

    public List<LivePiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

}
