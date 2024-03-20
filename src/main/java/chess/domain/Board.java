package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private Map<Position, Piece> pieces;

    public Board() {
        createBoard();
    }

    private void createBoard() {
        pieces = new HashMap<>();
        pieces.putAll(createEdgeRow(8, Team.BLACK));
        pieces.putAll(createPawn(7, Team.BLACK));
        pieces.putAll(createPawn(2, Team.WHITE));
        pieces.putAll(createEdgeRow(1, Team.WHITE));
    }

    public void move(Position oldPosition, Position newPosition) {
        if (pieces.containsKey(oldPosition)) {
            Piece thisPiece = pieces.get(oldPosition);

            List<Position> betweenPositions;
            if (pieces.containsKey(newPosition)) {
                betweenPositions = thisPiece.betweenPositionsWhenAttack(oldPosition, newPosition);
            } else {
                betweenPositions = thisPiece.betweenPositions(oldPosition, newPosition);
            }

            for (Position betweenPosition : betweenPositions) {
                if (pieces.containsKey(betweenPosition)) {
                    throw new IllegalArgumentException("이동을 가로막는 기물이 존재합니다.");
                }
            }

            if (pieces.containsKey(newPosition) && thisPiece.isSameTeamWith(pieces.get(newPosition))) {
                throw new IllegalArgumentException("해당 위치에 아군 기물이 존재합니다.");
            }

            pieces.put(newPosition, thisPiece.move());
            pieces.remove(oldPosition);
            return;
        }
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    public Map<Position, Character> mapPositionToCharacter() {
        return pieces.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().findCharacter()
                ));
    }

    private Map<Position, Piece> createPawn(int row, Team team) {
        return IntStream.rangeClosed(1, 8)
                .boxed()
                .collect(Collectors.toMap(
                        column -> Position.of(row, column),
                        column -> new Pawn(team, true)
                ));
    }

    private Map<Position, Piece> createEdgeRow(int row, Team team) {
        return new HashMap<>(Map.of(
                Position.of(row, 1), new Rook(team, true),
                Position.of(row, 2), new Knight(team, true),
                Position.of(row, 3), new Bishop(team, true),
                Position.of(row, 4), new Queen(team, true),
                Position.of(row, 5), new King(team, true),
                Position.of(row, 6), new Bishop(team, true),
                Position.of(row, 7), new Knight(team, true),
                Position.of(row, 8), new Rook(team, true)
        ));
    }
}
