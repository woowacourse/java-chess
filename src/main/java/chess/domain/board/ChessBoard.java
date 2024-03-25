package chess.domain.board;

import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.Piece;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Square> squares;

    public ChessBoard(Map<Position, Square> squares) {
        this.squares = new LinkedHashMap<>(squares);
    }

    public Map<Position, Square> getSquares() {
        return Collections.unmodifiableMap(squares);
    }

    public void move(TerminalPosition terminalPosition, Color currentTurn) {
        validate(terminalPosition, currentTurn);
        if (squares.get(terminalPosition.getEnd()) == Empty.getInstance()) {
            tryExchange(terminalPosition);
            return;
        }
        tryAttack(terminalPosition);
    }

    private void validate(TerminalPosition terminalPosition, Color currentTurn) {
        validateStartFriendly(terminalPosition, currentTurn);
        validateEndNotFriendly(terminalPosition, currentTurn);
    }

    // TODO: 하위 타입 캐스팅 대신 Map<Position, Piece>로 변경할지 고려
    private void validateStartFriendly(TerminalPosition terminalPosition, Color friendlyColor) {
        if (isEmpty(terminalPosition.getStart()) || isEnemy(terminalPosition.getStart(), friendlyColor)) {
            throw new IllegalArgumentException("시작 위치에 아군 체스말이 존재해야 합니다.");
        }
    }

    private void validateEndNotFriendly(TerminalPosition terminalPosition, Color currentTurn) {
        if (isNotEmpty(terminalPosition.getEnd()) && isFriendly(terminalPosition.getEnd(), currentTurn)) {
            throw new IllegalArgumentException("도착 위치에 아군 체스말이 존재할 수 없습니다.");
        }
    }

    private boolean isEmpty(Position position) {
        return squares.get(position) == Empty.getInstance();
    }

    private boolean isNotEmpty(Position position) {
        return !isEmpty(position);
    }

    private boolean isFriendly(Position position, Color friendlyColor) {
        Piece startPiece = (Piece) squares.get(position);
        return startPiece.isColor(friendlyColor);
    }

    private boolean isEnemy(Position position, Color friendlyColor) {
        return !isFriendly(position, friendlyColor);
    }

    // TODO: 예외 로직 세분화
    private void tryExchange(TerminalPosition terminalPosition) {
        Square startSquare = squares.get(terminalPosition.getStart());
        validateObstacle(startSquare.findPassPathTaken(terminalPosition));
        startSquare.move();

        Square tmp = squares.get(terminalPosition.getEnd());
        squares.put(terminalPosition.getEnd(), startSquare);
        squares.put(terminalPosition.getStart(), tmp);
    }

    private void tryAttack(TerminalPosition terminalPosition) {
        Square startSquare = squares.get(terminalPosition.getStart());
        validateObstacle(startSquare.findAttackPathTaken(terminalPosition));
        startSquare.move();

        squares.put(terminalPosition.getEnd(), startSquare);
        squares.put(terminalPosition.getStart(), Empty.getInstance());
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
