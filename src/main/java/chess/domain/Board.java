package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                Position position = new Position(x, y);
                board.put(position, new Blank(position));
            }
        }
        board.replace(new Position(1, 1), new Rook(new Position(1, 1), Color.WHITE));
        board.replace(new Position(8, 1), new Rook(new Position(8, 1), Color.WHITE));
        board.replace(new Position(1, 8), new Rook(new Position(1, 8), Color.BLACK));
        board.replace(new Position(8, 8), new Rook(new Position(8, 8), Color.BLACK));
        board.replace(new Position(5, 1), new King(new Position(5, 1), Color.WHITE));
        board.replace(new Position(5, 8), new King(new Position(5, 8), Color.BLACK));
    }

    public void move(Color turnColor, Position from, Position to) {
        Piece currentPiece = board.get(from);
        // TODO : instance of  지우기
        if (currentPiece instanceof Blank) {
            throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
        }
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
        Set<Position> movablePositions = currentPiece.findMovablePositions(to);
        Piece destinationPiece = board.get(to);
        if (isAllBlankCourses(movablePositions) && !destinationPiece.isSameColor(turnColor)) {
            board.replace(to, currentPiece.update(to));
            board.replace(from, new Blank(from));
        }
    }

    public Map<Position, PieceType> collectBoard() {
        return   board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }

    private boolean isAllBlankCourses(Set<Position> movablePositions) {
        return movablePositions.stream().map(board::get).allMatch(piece -> piece instanceof Blank);
    }
}
