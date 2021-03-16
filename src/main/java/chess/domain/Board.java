package chess.domain;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

public class Board {

    public static final int BOARD_SIZE = 8;


    private final List<Piece> pieces;
    private final List<Position> positions;

    public Board() {
        positions = Position.positions();
        pieces = new ArrayList<>();
        init();
    }

    private void init() {
        initPieces(0, WHITE);
        initPawns(1, WHITE);

        initPawns(6, BLACK);
        initPieces(7, BLACK);
    }

    private void initPieces(int y, TeamColor teamColor) {
        pieces.add(new Rook(teamColor, Position.of(0, y)));
        pieces.add(new Knight(teamColor, Position.of(1, y)));
        pieces.add(new Bishop(teamColor, Position.of(2, y)));
        pieces.add(new Queen(teamColor, Position.of(3, y)));
        pieces.add(new King(teamColor, Position.of(4, y)));
        pieces.add(new Bishop(teamColor, Position.of(5, y)));
        pieces.add(new Knight(teamColor, Position.of(6, y)));
        pieces.add(new Rook(teamColor, Position.of(7, y)));
    }

    private void initPawns(int y, TeamColor teamColor) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            pieces.add(new Pawn(teamColor, Position.of(x, y)));
        }
    }

    public Piece piece(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Position> positions() {
        return Collections.unmodifiableList(positions);
    }
}
