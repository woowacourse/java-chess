package chess.model;

import chess.model.piece.Piece;

import java.util.*;

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

        checkPiecePresent(sourceAndTarget, sourcePosition, targetPosition);

        Piece clonedPiece = tiles.get(sourcePosition).clonePiece();
        clonedPiece.signalMoved();

        tiles.put(sourcePosition, new Tile(sourcePosition, Optional.ofNullable(null)));
        tiles.put(targetPosition, new Tile(targetPosition, Optional.ofNullable(clonedPiece)));
    }

    private void checkPiecePresent(List<String> sourceAndTarget, String sourcePosition, String targetPosition) {
        if (checkPiecePresentInRoute(sourceAndTarget)) {
            throw new IllegalArgumentException("경로에 piece가 있어서 움직일 수 없습니다.");
        }

        if (checkPiecePresentInTarget(sourcePosition)
                && askTilePieceWhichTeam(sourcePosition).equals(askTilePieceWhichTeam(targetPosition))) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로는 움질일 수 없습니다.");
        }
    }

    // TODO: 2019-06-21 파라미터 이름 변경
    public boolean checkPiecePresentInRoute(List<String> sourceAndTarget) {
        Coordinate sourceCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(0, 1)));
        Coordinate sourceCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(1)));
        Coordinate targetCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(0, 1)));
        Coordinate targetCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(1)));

        // TODO: 2019-06-22 vector 어디서 만들지 생각
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));
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
