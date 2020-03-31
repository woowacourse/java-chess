package chess.domain;

import chess.Exceptions.NotMoveException;
import chess.PieceFactory;
import chess.domain.chesspieces.*;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Row;
import chess.domain.status.Result;
import chess.domain.status.Status;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {
    private final Map<Position, Piece> chessBoard = new HashMap<>();
    private boolean isKingTaken;

    public ChessBoard() {
        for (Map.Entry<Piece, List<Position>> entry : PieceFactory.create().entrySet()) {
            entry.getValue().forEach(position -> chessBoard.put(position,entry.getKey()));
        }
        isKingTaken = false;
    }

    public boolean move(Position from, Position to) {
        Piece source = chessBoard.get(from);
        Piece target = chessBoard.get(to);
        validateSamePosition(from, to);
        validateSource(source);
        validateIsPlayer(source, target);


        if (movable(from, to)) {
            chessBoard.put(to, source);
            chessBoard.remove(from);


            if (target instanceof King) {
                this.isKingTaken = true;
            }

            return true;
        }
        return false;
    }

    private void validateSamePosition(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        if (from == to) {
            throw new NotMoveException("같은 위치로 이동할 수 없습니다.");
        }
    }

    private void validateSource(Piece source) {
        if (Objects.isNull(source)) {
            throw new NotMoveException("empty에서는 이동할 수 없습니다.");
        }
    }

    private void validateIsPlayer(Piece source, Piece target) {
        if (Objects.nonNull(target) && source.isSamePlayer(target)) {
            throw  new NotMoveException("같은 Player의 기물로는 이동할 수 없습니다.");
        };
    }

    private boolean movable(Position from, Position to) {
        Piece source = chessBoard.get(from);
        Piece target = chessBoard.get(to);
        Direction direction = Direction.getDirection(from, to);

        return source.validateTileSize(from, to)
                && source.validateDirection(direction, target)
                && validateObstacles(getRoutes(from, to));
    }

    public boolean isGameOver() {
        return isKingTaken;
    }

    private boolean validateObstacles(List<Position> routes) {
        return routes.isEmpty();
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    private List<Position> getRoutes(Position from, Position to) {
        Direction direction = Direction.getDirection(from, to);
        return direction.getPositionsBetween(from, to);
    }

    public Status createStatus(Player player) {
        double score = getPlayerPieces(player)
                .stream()
                .mapToDouble(Piece::getScore)
                .sum();
        score -= PieceInfo.PAWN_SCORE_DIFF * getPawnCount(player);
        return new Status(player, score);
    }

    public Result createResult() {
        List<Status> statuses = Arrays.asList(createStatus(Player.WHITE), createStatus(Player.BLACK));
        return new Result(statuses);
    }

    private List<Piece> getPlayerPieces(Player player) {
        return chessBoard.values()
                .stream()
                .filter(piece -> piece.getPlayer() == player)
                .collect(Collectors.toList());
    }

    public int getPawnCountPerStage(List<Piece> columnLine, Player player) {
        return (int) columnLine.stream()
                .filter(piece -> piece instanceof Pawn)
                .map(piece -> (Pawn) piece)
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

    public List<Piece> getStage(Row row) {
        List<Piece> squares = new ArrayList<>();
        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            if (entry.getKey().getRow() == row)
                squares.add(entry.getValue());
        }
        return squares;
    }
}
