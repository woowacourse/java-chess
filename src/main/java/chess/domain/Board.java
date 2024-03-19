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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private List<Piece> pieces;

    public Board() {
        createBoard();
    }

    private void createBoard() {
        pieces = new ArrayList<>();
        pieces.addAll(createEdgeRow(8, Team.BLACK));
        pieces.addAll(createPawn(7, Team.BLACK));
        pieces.addAll(createPawn(2, Team.WHITE));
        pieces.addAll(createEdgeRow(1, Team.WHITE));
    }

    public Map<Position, Character> mapPositionToKind() {
        return pieces.stream()
                .collect(Collectors.toMap(
                        Piece::position,
                        Piece::findCharacter
                ));
    }

    private List<Piece> createPawn(int row, Team team) {
        return IntStream.rangeClosed(1, 8)
                .boxed()
                .map(column -> new Pawn(Position.of(row, column), team))
                .collect(Collectors.toList());
    }

    private List<Piece> createEdgeRow(int row, Team team) {
        return new ArrayList<>(List.of(
                new Rook(Position.of(row, 1), team),
                new Knight(Position.of(row, 2), team),
                new Bishop(Position.of(row, 3), team),
                new Queen(Position.of(row, 4), team),
                new King(Position.of(row, 5), team),
                new Bishop(Position.of(row, 6), team),
                new Knight(Position.of(row, 7), team),
                new Rook(Position.of(row, 8), team)
        ));
    }

    public List<Piece> pieces() {
        return Collections.unmodifiableList(pieces);
    }
}
