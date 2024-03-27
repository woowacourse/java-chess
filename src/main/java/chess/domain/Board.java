package chess.domain;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import chess.view.viewer.TeamViewer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void validateSameTeamByPosition(Position position, Team team) {
        validatePieceExistsOnPosition(position);
        if (!pieces.get(position).isSameTeamWith(team)) {
            throw new ImpossibleMoveException("%s이 움직일 차례입니다.".formatted(TeamViewer.show(team)));
        }
    }

    public Piece move(Movement movement) {
        validatePieceExistsOnPosition(movement.source());

        Piece thisPiece = pieces.get(movement.source());
        validateMovable(thisPiece, movement);
        validateSameTeamPieceExistsOnTargetPosition(movement.target(), thisPiece);
        validateBlockingPieceExists(thisPiece, movement);

        thisPiece = thisPiece.move();
        pieces.put(movement.target(), thisPiece);
        pieces.remove(movement.source());
        return thisPiece;
    }

    private void validatePieceExistsOnPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new ImpossibleMoveException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    public void validateMovable(Piece thisPiece, Movement movement) {
        if (!thisPiece.isMovable(movement, pieces.containsKey(movement.target()))) {
            throw new ImpossibleMoveException("해당 위치로 움직일 수 없습니다.");
        }
    }

    private void validateSameTeamPieceExistsOnTargetPosition(Position targetPosition, Piece thisPiece) {
        if (pieces.containsKey(targetPosition) && thisPiece.isSameTeamWith(pieces.get(targetPosition))) {
            throw new ImpossibleMoveException("해당 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validateBlockingPieceExists(Piece thisPiece, Movement movement) {
        if (isBlocked(thisPiece, movement)) {
            throw new ImpossibleMoveException("이동을 가로막는 기물이 존재합니다.");
        }
    }

    private boolean isBlocked(Piece thisPiece, Movement movement) {
        return thisPiece.findBetweenPositions(movement)
                .stream()
                .anyMatch(pieces::containsKey);
    }

    public GameResult findResultByScore() {
        double whiteScore = calculateScore(Team.WHITE);
        double blackScore = calculateScore(Team.BLACK);
        if (whiteScore > blackScore) {
            return GameResult.WHITE_WIN;
        }
        if (whiteScore < blackScore) {
            return GameResult.BLACK_WIN;
        }
        return GameResult.DRAW;
    }

    public double calculateScore(Team team) {
        double totalScore = findSameTeamPieces(team).mapToDouble(entry -> entry.getValue().point()).sum();
        if (sameColumnPawnCount(team) > 1) {
            totalScore = totalScore - sameColumnPawnCount(team) * 0.5;
        }
        return totalScore;
    }

    private int sameColumnPawnCount(Team team) {
        Stream<Position> pawnPositions = findSameTeamPieces(team)
                .filter(entry -> entry.getValue().isSameCharacter(new Character(team, Kind.PAWN)))
                .map(Entry::getKey);
        return Position.sameColumnPositionCount(pawnPositions);
    }

    public State checkState(Team currentTeam) {
        boolean isCheck = isChecked(currentTeam);
        boolean isMate = isMate(currentTeam);
        if (isCheck && isMate) {
            return State.CHECKMATE;
        }
        if (isCheck) {
            return State.CHECKED;
        }
        if (isMate) {
            return State.STALEMATE;
        }
        return State.NORMAL;
    }

    private boolean isChecked(Team attackedTeam) {
        Position kingPosition = getKingPosition(attackedTeam);
        return isBeingAttacked(attackedTeam, kingPosition);
    }

    private boolean isBeingAttacked(Team team, Position position) {
        return findSameTeamPieces(team.opponent())
                .anyMatch(entry -> isAttacking(entry.getValue(), new Movement(entry.getKey(), position)));
    }

    private Stream<Entry<Position, Piece>> findSameTeamPieces(Team team) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeamWith(team));
    }

    private boolean isAttacking(Piece thisPiece, Movement movement) {
        if (thisPiece.isMovable(movement, true)) {
            return !isBlocked(thisPiece, movement);
        }
        return false;
    }

    private boolean isMate(Team attackedTeam) {
        return findSameTeamPieces(attackedTeam)
                .allMatch(entry -> isCheckedAfterAllMoves(entry.getKey(), entry.getValue()));
    }

    private boolean isCheckedAfterAllMoves(Position position, Piece piece) {
        return findAllMovablePositions(position, piece)
                .allMatch(movablePosition -> isCheckedAfterMove(piece, new Movement(position, movablePosition)));
    }

    private Stream<Position> findAllMovablePositions(Position position, Piece piece) {
        return Stream.concat(findMovablePositions(position, piece), findAttackablePositions(position, piece))
                .filter(targetPosition -> !isBlocked(piece, new Movement(position, targetPosition)));
    }

    private Stream<Position> findMovablePositions(Position position, Piece piece) {
        return position.findAllMovablePosition(piece)
                .filter(targetPosition -> !pieces.containsKey(targetPosition));
    }

    private Stream<Position> findAttackablePositions(Position position, Piece piece) {
        return position.findAllMovablePosition(piece, true)
                .filter(targetPosition -> pieces.containsKey(targetPosition)
                        && !piece.isSameTeamWith(pieces.get(targetPosition)));
    }

    private boolean isCheckedAfterMove(Piece piece, Movement movement) {
        Board copiedBoard = new Board(new HashMap<>(this.pieces));
        copiedBoard.move(movement);
        return copiedBoard.isChecked(piece.team());
    }

    private Position getKingPosition(Team team) {
        Character myKingCharacter = new Character(team, Kind.KING);
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameCharacter(myKingCharacter))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(
                        "%s 왕이 체스판 위에 존재하기 않습니다.".formatted(TeamViewer.show(team))))
                .getKey();
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
