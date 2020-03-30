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
    private final Map<Position, Piece> chessBoard = new HashMap<>();
    private boolean isKingTaken;

    public ChessBoard() {
        Positions.getValues().forEach(position -> chessBoard.put(position, Empty.getInstance()));
        for (Map.Entry<Piece, List<Position>> entry : PieceInitPositionFactory.create().entrySet()) {
            entry.getValue().forEach(position -> chessBoard.put(position, entry.getKey()));
        }
        isKingTaken = false;
    }

    public boolean move(Position from, Position to) {
        validateException(from, to);

        if (validateMovement(from, to)) {
            Piece source = chessBoard.get(from);
            Piece target = chessBoard.get(to);
            chessBoard.put(to, source);
            chessBoard.put(from, Empty.getInstance());

            if (target instanceof King) {
                this.isKingTaken = true;
            }

            return true;
        }
        return false;
    }

    private void validateException(Position from, Position to) {
        if (from == to) {
            throw new NotMoveException("같은 위치로 이동할 수 없습니다.");
        }

        Piece source = chessBoard.get(from);
        Piece target = chessBoard.get(to);
        if (source instanceof Empty) {
            throw new NotMoveException("빈칸에서부터 이동할 수 없습니다.");
        }
        if (source.isSamePlayer(target)) {
            throw new NotMoveException("같은 Player의 위치로는 이동할 수 없습니다.");
        }
    }

    private boolean validateMovement(Position from, Position to) {
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
        return routes.isEmpty()
                || routes.stream()
                .allMatch(position -> chessBoard.get(position) instanceof Empty);
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    private List<Position> getRoutes(Position from, Position to) {
        Direction direction = Direction.getDirection(from, to);
        return direction.getPositionsBetween(from, to);
    }

    public Status createStatus(Player player) {
        System.out.println(getPlayerPieces(player).size());
        double score = getPlayerPieces(player)
                .stream()
                .mapToDouble(Piece::getScore)
                .sum();
        System.out.println("초기 score: "+score);
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
