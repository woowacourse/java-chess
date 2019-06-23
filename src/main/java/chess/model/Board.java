package chess.model;

import chess.model.piece.Piece;

import java.util.*;

import static chess.model.Direction.*;

public class Board {
    private Map<String, Tile> tiles;

    public Board(CreateStrategy strategy) {
        this.tiles = strategy.create();
    }

    public void movePiece(List<String> sourceAndTarget) {
        if (Objects.isNull(sourceAndTarget) || sourceAndTarget.isEmpty()) {
            throw new NullPointerException();
        }
        String sourcePosition = sourceAndTarget.get(0);
        String targetPosition = sourceAndTarget.get(1);

        checkMovablePiece(sourceAndTarget, sourcePosition, targetPosition);

        Piece clonedPiece = tiles.get(sourcePosition).clonePiece();

        tiles.put(sourcePosition, new Tile(sourcePosition, Optional.ofNullable(null)));
        tiles.put(targetPosition, new Tile(targetPosition, Optional.ofNullable(clonedPiece)));
    }

    private void checkMovablePiece(List<String> sourceAndTarget, String sourcePosition, String targetPosition) {
        Coordinate sourceCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(0, 1)));
        Coordinate sourceCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(1)));
        Coordinate targetCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(0, 1)));
        Coordinate targetCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(1)));
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        if (checkPiecePresentInRoute(sourceAndTarget, vector)) {
            throw new IllegalArgumentException("경로에 piece가 있어서 움직일 수 없습니다.");
        }

        String teamOfSourceTilePiece = askTilePieceWhichTeam(sourcePosition);
        String teamOfTargetTilePiece = askTilePieceWhichTeam(targetPosition);

        if (checkPiecePresentInTarget(sourcePosition) && teamOfSourceTilePiece.equals(teamOfTargetTilePiece)) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로는 움직일 수 없습니다.");
        }

        checkWhenPawn(sourcePosition, targetPosition, vector);
    }

    private void checkWhenPawn(String sourcePosition, String targetPosition, Vector vector) {
        if (tiles.get(sourcePosition).askPieceIfPawn()) {
            checkWhenDiagonal(targetPosition, vector);
            checkWhenVertical(targetPosition, vector);
        }
    }


    private void checkWhenDiagonal(String targetPosition, Vector vector) {
        if (Direction.isDiagonal(vector.getDirection())) {
            checkPiecePresentWhenDiagonal(targetPosition);
        }
    }

    private void checkPiecePresentWhenDiagonal(String targetPosition) {
        if (!tiles.get(targetPosition).isPiecePresent()) {
            throw new IllegalArgumentException("폰은 상대팀이 있을 경우만 대각선방향으로 움직일 수 있습니다.");
        }
    }

    private void checkWhenVertical(String targetPosition, Vector vector) {
        if ((vector.getDirection() == NORTH) || (vector.getDirection() == SOUTH)) {
            checkPiecePresentWhenVertical(targetPosition);
        }
    }

    private void checkPiecePresentWhenVertical(String targetPosition) {
        if (tiles.get(targetPosition).isPiecePresent()) {
            throw new IllegalArgumentException("폰은 대각선방향이 아닌 방향으로 움직여 상대팀을 잡을 수 없습니다.");
        }
    }

    // TODO: 2019-06-21 파라미터 이름 변경
    public boolean checkPiecePresentInRoute(List<String> sourceAndTarget, Vector vector) {
        Tile tile = tiles.get(sourceAndTarget.get(0));
        Route route = tile.findRouteFromPiece(vector);

        return checkEveryRoute(route);
    }

    private boolean checkEveryRoute(Route route) {
        for (String coordinates : tiles.keySet()) {
            if (route.contains(coordinates) && tiles.get(coordinates).isPiecePresent()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPiecePresentInTarget(String coordinate) {
        return tiles.get(coordinate).isPiecePresent();
    }

    public String askTilePieceWhichTeam(String coordinate) {
        return tiles.get(coordinate).askPieceWhichTeam();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tiles);
    }
}
