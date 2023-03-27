package chess.domain.board;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static chess.domain.pieces.component.Team.NEUTRALITY;
import static java.util.stream.Collectors.toList;

public class Board {

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(Map<Position, Piece> create) {
        return new Board(create);
    }

    public void movePiece(Position currentPosition, Position targetPosition) {
        Piece currentPiece = board.get(currentPosition);
        Direction movableDirection = Direction.findDirection(currentPosition, targetPosition);
        List<Piece> pieces = getLogicExistPiece(currentPosition, targetPosition, movableDirection);

        currentPiece.checkDirection(movableDirection);
        currentPiece.checkStep(currentPosition, movableDirection, pieces);
        currentPiece.checkExistPiece(pieces);
        currentPiece.checkSameTeam(currentPiece, board.get(targetPosition));
        move(currentPosition, targetPosition);
    }

    private List<Piece> getLogicExistPiece(final Position current, final Position target, final Direction movableDirection) {
        List<Piece> pieces = new ArrayList<>();
        if (movableDirection == Direction.KNIGHT) {
            return pieces;
        }

        Position nextPosition = current;
        do {
            nextPosition = moveNextPosition(nextPosition, movableDirection);
            pieces.add(board.get(nextPosition));
        } while (!nextPosition.equals(target));
        return List.copyOf(pieces);
    }

    private void move(final Position currentPosition, final Position targetPosition) {
        Piece currentPointPiece = board.get(currentPosition);
        board.put(currentPosition, new EmptyPiece(NEUTRALITY, Type.NO_PIECE));
        board.put(targetPosition, currentPointPiece);
    }

    private Position moveNextPosition(final Position currentPosition, final Direction movableDirection) {
        return currentPosition.nextPosition(movableDirection.getRank(), movableDirection.getFile());
    }

    public double calculateTeamScore(Team team) {
        double score = board.keySet()
                .stream()
                .filter(key -> !board.get(key).isPawn() && board.get(key).getTeam() == team)
                .mapToDouble(key -> board.get(key).getType().getScore())
                .sum();

        List<Position> pawns = findPawn(team);
        score += pawns.size() - (0.5 * countSameFilesPawn(pawns));
        return score;
    }

    private List<Position> findPawn(Team team) {
        return board.keySet()
                .stream()
                .filter(key -> board.get(key).isPawn() && board.get(key).getTeam() == team)
                .collect(toList());
    }

    private int countSameFilesPawn(List<Position> pawns) {
        return Arrays.stream(File.values())
                .mapToInt(file -> countSameFilePawn(pawns, file))
                .sum();
    }

    private int countSameFilePawn(List<Position> pawns, File file) {
        List<Position> collect = pawns
                .stream()
                .filter(position -> position.getFile() == file.getFile())
                .collect(toList());

        if (collect.size() >= 2) {
            return collect.size();
        }
        return 0;
    }

    public Map<Position, Piece> getBoard() {
        return this.board;
    }

    public Team getTeam(Position a) {
        return board.get(a).getTeam();
    }
}
