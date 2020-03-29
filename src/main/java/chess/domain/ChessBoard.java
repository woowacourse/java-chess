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
    private final Map<Position, Square> chessBoard = new LinkedHashMap<>();
    private boolean isKingTaken;

    public ChessBoard() {
        Positions.getValues().forEach(position -> chessBoard.put(position, Empty.getInstance()));
        for (Map.Entry<Piece, List<Position>> entry : PieceInitPositionFactory.create().entrySet()) {
            entry.getValue().forEach(position -> chessBoard.put(position, entry.getKey()));
        }
        isKingTaken = false;
    }

    public boolean move(Position from, Position to) {
        if (from == to) {
            throw new NotMoveException("같은 위치로 이동할 수 없습니다.");
        }

        Square source = chessBoard.get(from);
        Square target = chessBoard.get(to);
        if (source.movable(from, to)
                && validateObstacles(getRoutes(from, to))
                && !source.isSamePlayer(target)
                && validatePawnException(source, target, Direction.getDirection(from, to))) {
            chessBoard.put(to, source);
            chessBoard.put(from, Empty.getInstance());

            if (target instanceof King) {
                this.isKingTaken = isKingTaken();
            }

            return true;
        }
        return false;
    }

    public boolean isKingTaken() {
        if (this.isKingTaken) {
            return true;
        }
        return getKingCount() < 2;
    }

    public int getKingCount() {
        return (int) chessBoard.values()
                .stream()
                .filter(square -> square instanceof King)
                .count();
    }

    public boolean isGameOver() {
        return isKingTaken;
    }

    private boolean validatePawnException(Square source, Square target, Direction direction) {
        if (!(source instanceof Pawn)) {
            return true;
        }
        return ((Pawn) source).validate(direction, target);
    }

    private boolean validateObstacles(List<Position> routes) {
        return routes.isEmpty()
                || routes.stream()
                .allMatch(position -> chessBoard.get(position) instanceof Empty);
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
