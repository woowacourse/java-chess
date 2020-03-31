package chess.domains.board;

import chess.domains.piece.PieceColor;
import chess.domains.piece.PieceType;
import chess.domains.position.Column;
import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Board {
    public static final String INVALID_LOCATION_ERR_MSG = "위치를 잘못 입력하였습니다.";
    public static final String INVALID_ROUTE_ERR_MSG = "말을 뛰어넘을 수 없습니다.";
    public static final int TWO_KINGS = 2;
    public static final double SCORE_OF_PAWN_SAME_COLUMN = 0.5;
    public static final int COLUMN_SIZE = 8;

    private final Set<PlayingPiece> board = BoardFactory.getBoard();
    private PieceColor teamColor = PieceColor.WHITE;

    public List<PlayingPiece> showBoard() {
        List<PlayingPiece> showingBoard = new ArrayList<>(board);
        showingBoard.sort(PlayingPiece::compareTo);
        return showingBoard;
    }

    public PlayingPiece findPiece(Position location) {
        return board.stream()
                .filter(piece -> piece.has(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOCATION_ERR_MSG));
    }

    public void move(Position source, Position target) {
        PlayingPiece sourcePiece = findPiece(source);
        PlayingPiece targetPiece = findPiece(target);

        sourcePiece.checkSameColorWith(teamColor);
        sourcePiece.validMove(targetPiece);

        if (!sourcePiece.is(PieceType.KNIGHT)) {
            List<Position> route = sourcePiece.findRoute(targetPiece);
            validRoute(route);
        }

        exchange(sourcePiece, targetPiece);
        teamColor = teamColor.changeTeam();
    }

    private void validRoute(List<Position> route) {
        if (isBlocked(route)) {
            throw new IllegalArgumentException(INVALID_ROUTE_ERR_MSG);
        }
    }

    private boolean isBlocked(List<Position> route) {
        return route.stream()
                .map(this::findPiece)
                .noneMatch(PlayingPiece::isBlank);
    }

    private void exchange(PlayingPiece sourcePiece, PlayingPiece targetPiece) {
        this.board.remove(targetPiece);
        this.board.remove(sourcePiece);
        this.board.addAll(sourcePiece.moveTo(targetPiece));
    }

    public boolean isGameOver() {
        int count = (int) board.stream()
                .filter(playingPiece -> playingPiece.is(PieceType.KING))
                .count();
        return count != TWO_KINGS;
    }

    public double calculateScore(PieceColor teamColor) {
        double score = board.stream()
                .filter(playingPiece -> playingPiece.isMine(teamColor))
                .mapToDouble(PlayingPiece::score)
                .sum();

        int pawnCount = countOfPawnsInSameColumn(teamColor);

        return score - pawnCount * SCORE_OF_PAWN_SAME_COLUMN;
    }

    private int countOfPawnsInSameColumn(PieceColor teamColor) {
        Stream<PlayingPiece> myPawns = board.stream()
                .filter(playingPiece -> playingPiece.isMine(teamColor)
                        && playingPiece.is(PieceType.PAWN));

        int pawnCount = 0;
        for (Column column : Column.values()) {
            pawnCount += countValidPawns(myPawns, column);
        }
        return pawnCount;
    }

    private int countValidPawns(Stream<PlayingPiece> myPawns, Column column) {
        int sameColumnPiecesCount = (int) myPawns.filter(myPiece -> myPiece.isColumn(column))
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
