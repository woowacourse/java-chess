package chess.piece;

import chess.Position;
import chess.board.Pieces;

// NOTE: 살아있는 기물정보를 다룸
public class LivePiece {
    private final Piece piece;
    private Position position;

    public LivePiece(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public LivePiece(Position position, Piece piece) {
        this.piece = piece;
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    // 배열에 기물 채울 때 쓰는 index임
    public int getRowIndex() {
        return position.row().getIndex();
    }

    public int getColumnIndex() {
        return position.column().getIndex();
    }


    public char getSymbol() {
        char candidate = 'X';
        if (piece instanceof Rook) {
            candidate = 'R';
        }
        if (piece instanceof Knight) {
            candidate = 'N';
        }
        if (piece instanceof Bishop) {
            candidate = 'B';
        }
        if (piece instanceof Queen) {
            candidate = 'Q';
        }
        if (piece instanceof King) {
            candidate = 'K';
        }
        if (piece instanceof Pawn) {
            candidate = 'P';
        }

        // 팀에 따라 검정말이면 소문자로 변환하기
        if (piece.isBlack()) {
            candidate = Character.toLowerCase(candidate);
        }
        return candidate;
    }

    public boolean isSamePosition(Position other) {
        return position.equals(other);
    }

    public boolean canMove(Position start, Position end) {
        return piece.canMove(start, end);
    }

    public boolean isEndAble(Position endPosition, Pieces pieces) {
        return piece.isEndAble(endPosition, pieces);
    }

    public boolean isSameColor(Piece piece) {
        return this.piece.isSameColor(piece);
    }

    public boolean isPathAble(Position startPosition, Position endPosition, Pieces pieces) {
        return piece.isPathAble(startPosition, endPosition, pieces);
    }
}
