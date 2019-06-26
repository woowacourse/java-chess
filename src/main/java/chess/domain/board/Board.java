package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {
    private final Map<Tile, Piece> board;

    public Board(Map<Tile, Piece> boardState) {
        board = boardState;
    }

    public Piece at(String tileText) {
        return getPieceOnTile(Tile.of(tileText));
    }

    public Optional<Piece> order(String currentTileText, String goalTileText) {
        Pair<Tile, Tile> pointedTiles = getTiles(currentTileText, goalTileText);

        if (canMove(pointedTiles.getLeft(), pointedTiles.getRight())) {
            return move(pointedTiles.getLeft(), pointedTiles.getRight());
        }

        throw new InvalidMovingException("이동 불가능합니다.");
    }

    private Pair<Tile, Tile> getTiles(String currentTileText, String goalTileText) {
        try {
            return Pair.of(Tile.of(currentTileText), Tile.of(goalTileText));
        } catch (RuntimeException e) {
            throw new InvalidMovingException(e.getMessage());
        }
    }

    private boolean canMove(Tile currentTile, Tile goalTile) {
        try {
            Piece pieceOnCurrent = getPieceOnTile(currentTile);
            boolean haveTarget = haveTarget(pieceOnCurrent, goalTile); //target 위치 비었는가, 적이 있는가
            List<Tile> path = pieceOnCurrent.pathOf(currentTile, goalTile, haveTarget);
            checkPathDisturb(path);
            return true;
        } catch (InvalidMovingException e) {
            return false;
        }
    }

    private boolean haveTarget(Piece pieceOnCurrent, Tile goalTile) {
        if (!board.containsKey(goalTile)) {
            return false;
        }

        if (board.get(goalTile).isSameColorWith(pieceOnCurrent)) {
            throw new InvalidMovingException("같은 색깔의 말이 존재합니다.");
        }

        return true;
    }


    private Optional<Piece> move(Tile current, Tile goal) {
        Optional<Piece> pieceOnGoal = Optional.ofNullable(board.get(goal));
        board.put(goal, board.get(current));
        board.remove(current);
        return pieceOnGoal;
    }

    private Piece getPieceOnTile(Tile tile) {
        if (board.containsKey(tile)) {
            return board.get(tile);
        }
        throw new InvalidMovingException("말이 없습니다");
    }

    private void checkPathDisturb(List<Tile> path) {
        boolean haveDisturb = path.stream().anyMatch(board::containsKey);

        if (haveDisturb) {
            throw new InvalidMovingException("경로 상에 말이 있습니다.");
        }
    }

    public double status(PieceColor color) {
        return board.entrySet().stream()
                .filter(map -> map.getValue().getColor() == color)
                .map(map -> map.getKey())
                .collect(Collectors.groupingBy(Tile::getColumn))
                .values().stream()
                .mapToDouble(this::getLineStatus)
                .sum()
                ;
    }

    private double getLineStatus(List<Tile> tiles) {
        return calculateScore(
                tiles.stream()
                        .map(tile -> board.get(tile))
                        .collect(Collectors.toList())
        );
    }

    private double calculateScore(List<Piece> pieces) {
        double score = pieces.stream().mapToDouble(Piece::getScore).sum();
        long pawnCount = pieces.stream().filter(Piece::isPawn).count();

        if (pawnCount > 1) {
            score -= pawnCount * 0.5;
        }

        return score;
    }

    public Map<Tile, Piece> getBoard() {
        return board;
    }
}
