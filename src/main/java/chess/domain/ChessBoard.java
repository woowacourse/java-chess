package chess.domain;

import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<ChessBoardPosition, ChessPiece> mapInformation;

    private ChessBoard(final Map<ChessBoardPosition, ChessPiece> mapInformation) {
        this.mapInformation = mapInformation;
    }

    public static ChessBoard initialize() {
        Map<ChessBoardPosition, ChessPiece> mapInformation = new HashMap<>();
        initializeTeam(Team.BLACK);
        initializeTeam(Team.WHITE);
        return new ChessBoard(mapInformation);
    }

    private static void initializeTeam(Team team) {
        addChessPieces();
        addChessPieces();
        addChessPieces();
        addChessPieces();
        addChessPieces();
        addChessPieces();
    }

    private static void addChessPieces() {

    }

}
