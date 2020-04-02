package chess.domains.board;

import chess.domains.piece.Blank;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.piece.PieceType;
import chess.domains.position.Column;
import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    public static final String INVALID_ROUTE_ERR_MSG = "말을 뛰어넘을 수 없습니다.";
    public static final int TWO_KINGS = 2;
    public static final double SCORE_OF_PAWN_SAME_COLUMN = 0.5;
    public static final int COLUMN_SIZE = 8;

    private final Map<Position, Piece> board = BoardFactory.getBoard();
    private PieceColor teamColor = PieceColor.WHITE;

    public void initialize() {
        Map<Position, Piece> board = BoardFactory.getBoard();
        this.board.putAll(board);
        this.teamColor = PieceColor.WHITE;
    }

    public List<Piece> showBoard() {
        ArrayList<Position> positions = new ArrayList<>(board.keySet());
        Collections.sort(positions);

        return positions.stream()
                .map(board::get)
                .collect(Collectors.toList());
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        sourcePiece.checkSameColorWith(teamColor);
        sourcePiece.validMove(targetPiece, source, target);

        if (!sourcePiece.is(PieceType.KNIGHT)) {
            List<Position> route = source.findRoute(target);
            validRoute(route);
        }

        exchange(source, target);
        teamColor = teamColor.changeTeam();
    }

    private void validRoute(List<Position> route) {
        if (route.isEmpty()) {
            return;
        }
        if (isBlocked(route)) {
            throw new IllegalArgumentException(INVALID_ROUTE_ERR_MSG);
        }
    }

    private boolean isBlocked(List<Position> route) {
        return route.stream()
                .map(board::get)
                .anyMatch(piece -> !piece.is(PieceType.BLANK));
    }

    private void exchange(Position source, Position target) {
        Piece sourcePiece = board.remove(source);

        board.put(source, new Blank());
        board.put(target, sourcePiece);
    }

    public boolean isGameOver() {
        int count = (int) board.values()
                .stream()
                .filter(playingPiece -> playingPiece.is(PieceType.KING))
                .count();
        return count != TWO_KINGS;
    }

    public double calculateScore() {
        double score = board.values()
                .stream()
                .filter(playingPiece -> playingPiece.isMine(teamColor))
                .mapToDouble(Piece::score)
                .sum();

        int pawnCount = countOfPawnsInSameColumn();

        return score - pawnCount * SCORE_OF_PAWN_SAME_COLUMN;
    }

    public double calculateScore(PieceColor pieceColor) {
        double score = board.values()
                .stream()
                .filter(playingPiece -> playingPiece.isMine(pieceColor))
                .mapToDouble(Piece::score)
                .sum();

        int pawnCount = countOfPawnsInSameColumn();

        return score - pawnCount * SCORE_OF_PAWN_SAME_COLUMN;
    }

    private int countOfPawnsInSameColumn() {
        int pawnCount = 0;
        for (Column column : Column.values()) {
            pawnCount += countValidPawns(column);
        }
        return pawnCount;
    }

    private int countValidPawns(Column column) {
        int sameColumnPiecesCount = (int) board.keySet()
                .stream()
                .filter(position -> position.isColumn(column))
                .map(board::get)
                .filter(playingPiece -> playingPiece.isMine(teamColor)
                        && playingPiece.is(PieceType.PAWN))
                .count();

        if (sameColumnPiecesCount > 1) {
            return sameColumnPiecesCount;
        }
        return 0;
    }

    public PieceColor getTeamColor() {
        return teamColor;
    }
}
