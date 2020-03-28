package chess.domain;

import chess.Exceptions.NotMoveException;
import chess.PieceInitPositionFactory;
import chess.domain.chesspieces.*;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Row;
import chess.domain.status.Result;
import chess.domain.status.Status;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {
    private final static Map<Position, Square> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        Positions.getValues().forEach(position -> chessBoard.put(position, Empty.getInstance()));
        for (Map.Entry<Piece, List<Position>> entry : PieceInitPositionFactory.create().entrySet()) {
            entry.getValue().forEach(position -> chessBoard.put(position, entry.getKey()));
        }
    }

    public boolean move(Position from, Position to) {
        if (from == to) {
            throw new NotMoveException("같은 위치로는 이동을 못합니다.");
        }

        Square source = chessBoard.get(from);
        Square target = chessBoard.get(to);
        if (source.movable(from, to)
                && validateObstacles(getRoutes(from, to))
                && !source.isSamePlayer(target)
                && validatePawnException(source, target, Direction.getDirection(from, to))) {
            chessBoard.put(to, source);
            chessBoard.put(from, Empty.getInstance());
            return true;
        }
        return false;
    }

    // Source가 예외일 때 1) 전진 예외 (전진의 target은 무조건 empty여야 한다.)
    // 2) 대각선 공격일 때, 같은 팀이면 안되고, empty이면 안된다. 무조건 다른 팀이어야 한다.
    private boolean validatePawnException(Square source, Square target, Direction direction) {
        if (source.getClass() != Pawn.class) {
            return true;
        }
        return ((Pawn) source).validateMoveForward(target, direction)
                && ((Pawn) source).validateAttack(target, direction);
    }

    private boolean validateObstacles(List<Position> routes) {
        return routes.stream()
                .anyMatch(position -> chessBoard.get(position).getClass() == Empty.class);
    }

    public Map<Position, Square> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    private List<Position> getRoutes(Position from, Position to) {
        Direction direction = Direction.getDirection(from, to);
        return direction.getPositionsBetween(from, to);
    }

    public Status createStatus(Player player) {
        double result = getPlayerPieces(player)
                .stream()
                .mapToDouble(Piece::getScore)
                .sum();
        result -= PieceInfo.PAWN_SCORE_DIFF * getPawnCount(player);
        return new Status(player, result);
    }

    private List<Piece> getPlayerPieces(Player player) {
        return chessBoard.values()
                .stream()
                .filter(square -> square instanceof Piece)
                .map(square -> (Piece) square)
                .filter(piece -> piece.getPlayer() == player)
                .collect(Collectors.toList());
    }

    public int getPawnCountPerStage(List<Square> columnLine, Player player) {
        return (int) columnLine.stream()
                .filter(square -> square.getClass() == Pawn.class)
                .map(square -> (Pawn) square)
                .filter(pawn -> pawn.getPlayer() == player)
                .count();
    }

    public int getPawnCount(Player player) {
        int count = 0;
        for (Row row : Row.values()) {
            int value = getPawnCountPerStage(getStage(row), player);
            if (value != 1) {
                count += value;
            }
        }
        return count;
    }

    public List<Square> getStage(Row row) {
        List<Square> squares = new ArrayList<>();
        for (Map.Entry<Position, Square> entry : chessBoard.entrySet()) {
            if (entry.getKey().getRow() == row)
                squares.add(entry.getValue());
        }
        return squares;
    }

    public Result getResult() {
        List<Status> statuses = Arrays.asList(createStatus(Player.WHITE), createStatus(Player.BLACK));
        return new Result(statuses);
    }
}
