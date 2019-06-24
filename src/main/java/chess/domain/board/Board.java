package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.PAWN;

public class Board {
    private final Map<Tile, Optional<Piece>> board;

    public Board(Map<Tile, Piece> boardState) {
        board = new HashMap<>();
        Tile.stream()
                .forEach(tile ->
                        board.put(tile, getState(boardState, tile))
                );
    }

    private void checkPathDisturb(List<Tile> path) {
        boolean haveDisturb = path.stream().anyMatch(tile -> board.get(tile).isPresent());
        if (haveDisturb) {
            throw new IllegalArgumentException("경로 상에 말이 있습니다.");
        }
    }

    private static double getLineScore(List<PieceType> types) {
        double score = types.stream().mapToDouble(PieceType::getScore).sum();
        long pawnCount = types.stream().filter(x -> x.equals(PAWN)).count();

        if (pawnCount > 1) {
            score -= pawnCount * 0.5;
        }

        return score;
    }

    private Optional<Piece> getState(Map<Tile, Piece> board, Tile tile) {
        if (board.keySet().contains(tile)) {
            return Optional.of(board.get(tile));
        }
        return Optional.empty();
    }

    public Optional<Piece> at(String tileText) {
        Tile tile = Tile.of(tileText);
        return board.get(tile);
    }

    public void order(String currentTileText, String goalTileText) {
        Tile currentTile = Tile.of(currentTileText);
        Tile goalTile = Tile.of(goalTileText);
        if (canMove(currentTile, goalTile)) {
            move(currentTile, goalTile);
            return;
        }

        throw new IllegalArgumentException("이동 불가능합니다.");
    }

    private boolean canMove(Tile currentTile, Tile goalTile) {
        try {
            Piece pieceOnCurrent = getPieceOnTile(currentTile);
            boolean empty = haveTarget(pieceOnCurrent, goalTile); //target 위치 비었는가, 적이 있는가
            List<Tile> path = pieceOnCurrent.pathOf(currentTile, goalTile, empty);
            checkPathDisturb(path);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private boolean haveTarget(Piece pieceOnCurrent, Tile goalTile) {
        boolean empty = true;
        if (board.get(goalTile).isPresent()) {
            if (pieceOnCurrent.isSameColorWith(board.get(goalTile).get())) {
                throw new IllegalArgumentException("같은 색깔의 말이 존재합니다.");
            }
            empty = false;
        }
        return empty;
    }


    private void move(Tile current, Tile goal) {
        board.put(goal, board.get(current));
        board.put(current, Optional.empty());
    }

    private Piece getPieceOnTile(Tile tile) {
        Optional<Piece> piece = board.get(tile);
        if (piece.isPresent()) {
            return piece.get();
        }
        throw new IllegalArgumentException("움직일 말이 없습니다");
    }

    public double status(PieceColor color) {
        return Column.stream()
                .map(column ->
                        Tile.tilesOf(column, Tile::of)
                                .collect(Collectors.toList()))
                .map(x -> findPieceTypesByColor(x, color))
                .mapToDouble(Board::getLineScore)
                .sum()
                ;
    }

    private List<PieceType> findPieceTypesByColor(List<Tile> tiles, PieceColor color) {
        return tiles.stream()
                .map(board::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(piece -> piece.getColor().equals(color))
                .map(Piece::getType)
                .collect(Collectors.toList())
                ;
    }
}
