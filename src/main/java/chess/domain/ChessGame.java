package chess.domain;

import chess.view.CommendDto;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class ChessGame {

    public final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void handleMove(CommendDto commendDto, Color currentTurn) {
        Position from = Position.from(commendDto.from());
        Position to = Position.from(commendDto.to());
        List<Position> movablePositions = generateMovablePositions(from, currentTurn);
        movePiece(movablePositions, from, to);
    }

    public List<Position> generateMovablePositions(Position fromPosition, Color currentTurn) {
        Piece piece = board.findPieceByPosition(fromPosition);
        if (piece.isSameTeam(currentTurn.opposite())) {
            throw new IllegalArgumentException("다른 팀의 기물을 움직일 수 없습니다.");
        }
        Map<Direction, Deque<Position>> expectedAllPositions = piece.calculateAllDirectionPositions(fromPosition);
        return generateValidPositions(expectedAllPositions, piece);
    }

    private List<Position> generateValidPositions(Map<Direction, Deque<Position>> expectedAllPositions, Piece piece) {
        return expectedAllPositions.keySet()
                .stream()
                .map(direction -> filterInvalidPositions(expectedAllPositions.get(direction), direction, piece))
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
