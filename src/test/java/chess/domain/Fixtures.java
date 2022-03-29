package chess.domain;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Team;
import java.util.HashMap;
import java.util.Map;

public class Fixtures {

    private static final Map<String, Piece> stringPieceMap = new HashMap<>();

    static {
        stringPieceMap.put(".", new EmptyPiece());
        stringPieceMap.put("R", new Rook(Team.BLACK));
        stringPieceMap.put("N", new Knight(Team.BLACK));
        stringPieceMap.put("B", new Bishop(Team.BLACK));
        stringPieceMap.put("Q", new Queen(Team.BLACK));
        stringPieceMap.put("K", new King(Team.BLACK));
        stringPieceMap.put("P", new Pawn(Team.BLACK));
        stringPieceMap.put("r", new Rook(Team.WHITE));
        stringPieceMap.put("n", new Knight(Team.WHITE));
        stringPieceMap.put("b", new Bishop(Team.WHITE));
        stringPieceMap.put("q", new Queen(Team.WHITE));
        stringPieceMap.put("k", new King(Team.WHITE));
        stringPieceMap.put("p", new Pawn(Team.WHITE));

    }

    public static Map<Position, Piece> stringToBoard(String text) {
        Map<Position, Piece> board = new HashMap<>();
        int rankY = 8;
        for (String rank : text.split(" ")) {
            int fileX = 1;
            for (String pieceString : rank.split("")) {
                board.put(new Position(
                                Column.numberOf(fileX), Rank.numberOf(rankY)
                        ),
                        stringPieceMap.get(pieceString)
                );
                fileX++;
            }
            rankY--;
        }
        return board;
    }
}
