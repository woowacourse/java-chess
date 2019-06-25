package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.PAWN;

public class Board {
    private final Map<Tile, Piece> board;

    public Board(Map<Tile, Piece> boardState) {
        board = boardState;
    }

    public Piece at(String tileText) {
        return getPieceOnTile(Tile.of(tileText));
    }

    public Optional<Piece> order(String currentTileText, String goalTileText) {
        Tile currentTile = Tile.of(currentTileText);
        Tile goalTile = Tile.of(goalTileText);

        if (canMove(currentTile, goalTile)) {
            return move(currentTile, goalTile);
        }

        throw new InvalidMovingException("이동 불가능합니다.");
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
        boolean haveDisturb = path.stream()
                .anyMatch(board::containsKey);
        if (haveDisturb) {
            throw new InvalidMovingException("경로 상에 말이 있습니다.");
        }
    }

    public double status(PieceColor color) {
        return  Column.stream()
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
                .filter(board::containsKey)
                .map(board::get)
                .filter(piece -> piece.isColor(color))
                .map(Piece::getType)
                .collect(Collectors.toList())
                ;
    }

    private static double getLineScore(List<PieceType> types) {
        double score = types.stream().mapToDouble(PieceType::getScore).sum();
        long pawnCount = types.stream().filter(x -> x.equals(PAWN)).count();

        if (pawnCount > 1) {
            score -= pawnCount * 0.5;
        }

        return score;
    }

    public Map getBoard() {
        return board;
    }
}
