package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<ChessBoardPosition, ChessPiece> mapInformation;

    private ChessBoard(final Map<ChessBoardPosition, ChessPiece> mapInformation) {
        this.mapInformation = mapInformation;
    }

    public static ChessBoard initialize() {
        Map<ChessBoardPosition, ChessPiece> mapInformation = new HashMap<>();
        initializeTeam(mapInformation, Team.BLACK);
        initializeTeam(mapInformation, Team.WHITE);
        return new ChessBoard(mapInformation);
    }

    private static void initializeTeam(Map<ChessBoardPosition, ChessPiece> mapInformation, Team team) {
        addChessPieces(mapInformation, Pawn.create(team));
        addChessPieces(mapInformation, Knight.create(team));
        addChessPieces(mapInformation, Bishop.create(team));
        addChessPieces(mapInformation, Rook.create(team));
        addChessPieces(mapInformation, Queen.create(team));
        addChessPieces(mapInformation, King.create(team));
    }

    private static void addChessPieces(
            Map<ChessBoardPosition, ChessPiece> mapInformation, Map<ChessBoardPosition, ChessPiece> chessPieces) {
        mapInformation.putAll(chessPieces);
    }

    public Map<ChessBoardPosition, ChessPiece> getMapInformation() {
        return mapInformation;
    }
}
