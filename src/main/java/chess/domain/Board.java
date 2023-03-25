package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.NoneEmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.RelativePosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.PAWN;

public final class Board {

    private static final int LINE_SIZE = 8;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePiece(final Position from, final Position to) {
        validateSourceEmpty(from);
        validateObstacleInPath(from, to);

        NoneEmptyPiece source = (NoneEmptyPiece) board.get(from);
        Piece target = board.get(to);
        if (source.isMobile(RelativePosition.of(from, to), target)) {
            board.put(to, source);
            board.put(from, new EmptyPiece());
        }
    }

    private void validateSourceEmpty(final Position from) {
        if (board.get(from).isEmpty()) {
            throw new IllegalArgumentException("조작할 수 있는 말이 없습니다.");
        }
    }

    private void validateObstacleInPath(final Position from, final Position to) {
        NoneEmptyPiece source = (NoneEmptyPiece) board.get(from);
        List<Position> obstaclePositionsInPath = source.getObstacleCheckingPositions(from, to);

        if (hasObstacle(obstaclePositionsInPath)) {
            throw new IllegalArgumentException("해당 경로의 다른 말을 건너뛸 수 없습니다.");
        }
    }

    private boolean hasObstacle(final List<Position> obstaclePositionsInPath) {
        return obstaclePositionsInPath.stream()
                .anyMatch(position -> !board.get(position).isEmpty());
    }

    public boolean isKingDead(final Team team) {
        return board.values()
                .stream()
                .noneMatch(piece -> piece.isPieceType(KING) && piece.isTeam(team));
    }

    public boolean isTeamInPositionMatched(final Position position, final Team expected) {
        return board.get(position).isTeam(expected);
    }

    private List<List<Piece>> sortBoard() {
        List<Position> positions = sortPosition();
        List<List<Piece>> sortedBoard = new ArrayList<>();
        for (int i = 0; i < LINE_SIZE; i++) {
            List<Piece> line = sortLine(positions, i);
            sortedBoard.add(line);
        }
        return sortedBoard;
    }

    private List<Piece> sortLine(final List<Position> positions, final int i) {
        List<Piece> line = new ArrayList<>();
        for (int j = 0; j < LINE_SIZE; j++) {
            Piece piece = board.get(positions.get(j + LINE_SIZE * i));
            line.add(piece);
        }
        return line;
    }

    private List<Position> sortPosition() {
        List<Position> positions = new ArrayList<>(board.keySet());
        positions.sort((p1, p2) -> {
            if (p1.getRow() == p2.getRow()) {
                return p1.getColumn() - p2.getColumn();
            }
            return p2.getRow() - p1.getRow();
        });
        return positions;
    }


    private long countPawnsInSameColumn(final int column, final Team team) {
        long pawnCount = board.keySet()
                .stream()
                .filter(position -> position.isColumn(column))
                .map(board::get)
                .filter(piece -> piece.isPieceType(PAWN) && piece.isTeam(team))
                .count();

        if (pawnCount > 1) {
            return pawnCount;
        }
        return 0;
    }

    public List<List<Piece>> getBoard() {
        return sortBoard();
    }

    public List<Double> getScores(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isTeam(team))
                .map(Piece::getScore)
                .collect(Collectors.toList());
    }

    public double getMinusScore(final Team team) {
        long pawnCount = 0;
        for (int i = 0; i < LINE_SIZE; i++) {
            pawnCount += countPawnsInSameColumn(i, team);
        }
        return pawnCount * 0.5;
    }

    public Map<Position, Piece> board() {
        return Map.copyOf(board);
    }
}
