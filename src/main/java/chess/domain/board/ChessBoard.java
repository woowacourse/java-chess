package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new LinkedHashMap<>(pieces);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public void move(TerminalPosition terminalPosition, Color currentTurn) {
        validate(terminalPosition, currentTurn);
        if (pieces.get(terminalPosition.getEnd()) == Empty.getInstance()) {
            passPiece(terminalPosition);
            return;
        }
        attackPiece(terminalPosition);
    }

    private void validate(TerminalPosition terminalPosition, Color currentTurn) {
        validateStartFriendly(terminalPosition.getStart(), currentTurn);
        validateEndNotFriendly(terminalPosition.getEnd(), currentTurn);
    }

    private void validateStartFriendly(Position startPosition, Color friendlyColor) {
        if (isEmpty(startPosition) || isEnemy(startPosition, friendlyColor)) {
            throw new IllegalArgumentException("시작 위치에 아군 체스말이 존재해야 합니다.");
        }
    }

    private void validateEndNotFriendly(Position endPosition, Color currentTurn) {
        if (isNotEmpty(endPosition) && isFriendly(endPosition, currentTurn)) {
            throw new IllegalArgumentException("도착 위치에 아군 체스말이 존재할 수 없습니다.");
        }
    }

    private boolean isEmpty(Position position) {
        return pieces.get(position) == Empty.getInstance();
    }

    private boolean isNotEmpty(Position position) {
        return !isEmpty(position);
    }

    private boolean isFriendly(Position position, Color friendlyColor) {
        Piece startPiece = pieces.get(position);
        return startPiece.isColor(friendlyColor);
    }

    private boolean isEnemy(Position position, Color friendlyColor) {
        return !isFriendly(position, friendlyColor);
    }

    private void passPiece(TerminalPosition terminalPosition) {
        Piece startPiece = pieces.get(terminalPosition.getStart());
        validateObstacle(startPiece.findPassPathTaken(terminalPosition));
        startPiece.move();

        exchange(terminalPosition, startPiece);
    }

    private void exchange(TerminalPosition terminalPosition, Piece startPiece) {
        Piece temp = pieces.get(terminalPosition.getEnd());
        pieces.put(terminalPosition.getEnd(), startPiece);
        pieces.put(terminalPosition.getStart(), temp);
    }

    private void attackPiece(TerminalPosition terminalPosition) {
        Piece startPiece = pieces.get(terminalPosition.getStart());
        validateObstacle(startPiece.findAttackPathTaken(terminalPosition));
        startPiece.move();

        pieces.put(terminalPosition.getEnd(), startPiece);
        pieces.put(terminalPosition.getStart(), Empty.getInstance());
    }

    private void validateObstacle(List<Position> pathTaken) {
        if (isObstacleIn(pathTaken)) {
            throw new IllegalArgumentException("경로에 장애물이 있습니다.");
        }
    }

    private boolean isObstacleIn(List<Position> pathTaken) {
        return pathTaken.stream()
                .anyMatch(this::isNotEmpty);
    }
}
