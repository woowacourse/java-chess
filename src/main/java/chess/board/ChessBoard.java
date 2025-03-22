package chess.board;

import chess.piece.Piece;
import java.util.Collections;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> boardMap;

    public ChessBoard(Map<Position, Piece> boardMap) {
        this.boardMap = boardMap;
    }

    public GameState movePiece(Color currentTurnColor, Position start, Position end) {
        if(isEmpty(start)) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        if(!boardMap.get(start).isSameColor(currentTurnColor)) {
            throw new IllegalArgumentException("상대편의 말은 움직일 수 없습니다.");
        }
        Piece piece = boardMap.get(start);
        if(!piece.canMoveToDestination(this, start, end)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }

        if (boardMap.containsKey(end) && piece.isGameOver()) {
            return GameState.END;
        }
        Piece movedPiece = boardMap.remove(start);
        boardMap.put(end, movedPiece);
        return GameState.PLAY;
    }

    public boolean isEmpty(Position position) {
        return !boardMap.containsKey(position);
    }

    public boolean isNotSameColor(Position position, Color color) {
        return isEmpty(position) || !boardMap.get(position).isSameColor(color);
    }

    public boolean isOppositeColor(Position position, Color color) {
        return !isEmpty(position) && !boardMap.get(position).isSameColor(color);
    }

    public Map<Position, Piece> getBoardMap() {
        return Collections.unmodifiableMap(boardMap);
    }
}
