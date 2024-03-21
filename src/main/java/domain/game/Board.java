package domain.game;

import domain.position.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public void movePiece(final TeamColor teamColor, final Position source, final Position destination) {
        validateRequest(teamColor, source);

        Piece piece = chessBoard.get(source);
        if (!piece.isMovable(source, destination, parseOtherPiecePositions(source))) {
            throw new IllegalArgumentException("이동 위치까지 이동할 수 없습니다.");
        }

        // 이동 위치에 아군 기물이 존재
        if (isPieceExist(destination)) {
            if (chessBoard.get(destination).hasColor(teamColor)) {
                throw new IllegalArgumentException("이동 위치에 아군 기물이 존재합니다.");
            }
        }
        // 공격 없이 빈 칸으로 이동하는 경우
        chessBoard.put(destination, piece);
        chessBoard.remove(source);
    }

    private Set<Position> parseOtherPiecePositions(final Position source) {
        Set<Position> positions = new HashSet<>(chessBoard.keySet());
        positions.remove(source);
        return positions;
    }

    private void validateRequest(final TeamColor teamColor, final Position source) {
        if (!chessBoard.containsKey(source) || !chessBoard.get(source).hasColor(teamColor)) {
            throw new IllegalArgumentException("차례가 맞지 않습니다.");
        }
    }

    private boolean isPieceExist(Position position) {
        return chessBoard.containsKey(position);
    }
}
