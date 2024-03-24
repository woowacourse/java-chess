package chess.domain;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private static final Color START_COLOR = Color.WHITE;

    public final Board board;
    private Color currentTurn;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTurn = START_COLOR;
    }

    public ChessGame(Board board, Color currentTurn) {
        this.board = board;
        this.currentTurn = currentTurn;
    }

    public void handleMove(Position from, Position to) {
        List<Position> movablePositions = generateMovablePositions(from);
        movePiece(movablePositions, from, to);
        this.currentTurn = this.currentTurn.opposite();
    }

    public List<Position> generateMovablePositions(Position fromPosition) {
        Piece fromPiece = board.findPieceByPosition(fromPosition);
        if (fromPiece.isSameTeam(currentTurn.opposite())) {
            throw new IllegalArgumentException("다른 팀의 기물을 움직일 수 없습니다. 현재 턴 : " + currentTurn.name());
        }
        Map<Direction, Deque<Position>> expectedAllPositions = fromPiece.calculateAllDirectionPositions(fromPosition);
        return generateValidPositions(expectedAllPositions, fromPiece);
    }

    private List<Position> generateValidPositions(Map<Direction, Deque<Position>> expectedAllPositions, Piece fromPiece) {
        return expectedAllPositions.keySet()
                .stream()
                .map(direction -> filterInvalidPositions(expectedAllPositions.get(direction), direction, fromPiece))
                .flatMap(List::stream)
                .toList();
    }

    private List<Position> filterInvalidPositions(Deque<Position> expectedPositions, Direction direction, Piece piece) {
        List<Position> result = new ArrayList<>();
        Position currentPosition = expectedPositions.poll();
        while (isEmptySpace(direction, piece, currentPosition)) {
            result.add(currentPosition);
            currentPosition = expectedPositions.poll();
        }
        if (isEnemySpace(direction, piece, currentPosition)) {
            result.add(currentPosition);
        }
        return result;
    }

    private boolean isEmptySpace(Direction direction, Piece piece, Position currentPosition) {
        return currentPosition != null
                && piece.isForward(direction)
                && board.isEmptySpace(currentPosition);
    }

    private boolean isEnemySpace(Direction direction, Piece piece, Position currentPosition) {
        return currentPosition != null
                && piece.isAttack(direction)
                && board.hasPiece(currentPosition)
                && !board.findPieceByPosition(currentPosition).isSameTeam(piece);
    }

    public void movePiece(List<Position> movablePositions, Position from, Position to) {
        if (movablePositions.contains(to)) {
            board.movePiece(from, to);
            return;
        }
        throw new IllegalArgumentException("기물을 해당 위치로 이동시킬 수 없습니다.");
    }

    public Board getBoard() {
        return board;
    }
}
