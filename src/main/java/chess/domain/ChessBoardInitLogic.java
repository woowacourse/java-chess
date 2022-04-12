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

public class ChessBoardInitLogic {

    private ChessBoardInitLogic() {}

    public static Map<ChessBoardPosition, ChessPiece> initialize() {
        Map<ChessBoardPosition, ChessPiece> boardData = new HashMap<>();
        initializeTeam(boardData, Team.BLACK);
        initializeTeam(boardData, Team.WHITE);
        return boardData;
    }

    private static void initializeTeam(Map<ChessBoardPosition, ChessPiece> boardData, Team team) {
        addChessPieces(boardData, Pawn.create(team));
        addChessPieces(boardData, Knight.create(team));
        addChessPieces(boardData, Bishop.create(team));
        addChessPieces(boardData, Rook.create(team));
        addChessPieces(boardData, Queen.create(team));
        addChessPieces(boardData, King.create(team));
    }

    private static void addChessPieces(Map<ChessBoardPosition, ChessPiece> boardData,
                                       Map<ChessBoardPosition, ChessPiece> additionalBoardData) {
        boardData.putAll(additionalBoardData);
    }
}
