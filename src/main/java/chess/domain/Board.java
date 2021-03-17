package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;
import static java.util.stream.Collectors.toMap;

public class Board {

    public static final int BOARD_SIZE = 8;

    private final Map<Position, Piece> piecePositions = new HashMap<>();

    public Board() {
        init();
    }

    private void init() {
        initPieces(0, WHITE);
        initPawns(1, WHITE);

        initPawns(6, BLACK);
        initPieces(7, BLACK);
    }

    private void initPieces(int y, TeamColor teamColor) {
        piecePositions.put(Position.of(0, y), new Rook(teamColor));
        piecePositions.put(Position.of(1, y), new Knight(teamColor));
        piecePositions.put(Position.of(2, y), new Bishop(teamColor));
        piecePositions.put(Position.of(3, y), new Queen(teamColor));
        piecePositions.put(Position.of(4, y), new King(teamColor));
        piecePositions.put(Position.of(5, y), new Bishop(teamColor));
        piecePositions.put(Position.of(6, y), new Knight(teamColor));
        piecePositions.put(Position.of(7, y), new Rook(teamColor));
    }

    private void initPawns(int y, TeamColor teamColor) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            piecePositions.put(Position.of(x, y), new Pawn(teamColor));
        }
    }

    public Map<Position, String> nameGroupingByPosition() {
        return piecePositions.entrySet().stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().nameByTeamColor()
                ));
    }

    public Piece piece(Position position) {
        return piecePositions.get(position);
    }
}
