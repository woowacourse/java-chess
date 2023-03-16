package chess.domain.board;

import static chess.domain.board.FileCoordinate.A;
import static chess.domain.board.FileCoordinate.B;
import static chess.domain.board.FileCoordinate.C;
import static chess.domain.board.FileCoordinate.D;
import static chess.domain.board.FileCoordinate.E;
import static chess.domain.board.FileCoordinate.F;
import static chess.domain.board.FileCoordinate.G;
import static chess.domain.board.FileCoordinate.H;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> boards = new HashMap<>();
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            Color color = Color.of(rankCoordinate);
            RankType rankType = RankType.of(rankCoordinate);
            if (rankType.isSideRank()) {
                boards.put(new Position(A, rankCoordinate), new Rook(color));
                boards.put(new Position(B, rankCoordinate), new Knight(color));
                boards.put(new Position(C, rankCoordinate), new Bishop(color));
                boards.put(new Position(D, rankCoordinate), new Queen(color));
                boards.put(new Position(E, rankCoordinate), new King(color));
                boards.put(new Position(F, rankCoordinate), new Bishop(color));
                boards.put(new Position(G, rankCoordinate), new Knight(color));
                boards.put(new Position(H, rankCoordinate), new Rook(color));
            } else if (rankType.isPawnRank()) {
                boards.put(new Position(A, rankCoordinate), new Pawn(color));
                boards.put(new Position(B, rankCoordinate), new Pawn(color));
                boards.put(new Position(C, rankCoordinate), new Pawn(color));
                boards.put(new Position(D, rankCoordinate), new Pawn(color));
                boards.put(new Position(E, rankCoordinate), new Pawn(color));
                boards.put(new Position(F, rankCoordinate), new Pawn(color));
                boards.put(new Position(G, rankCoordinate), new Pawn(color));
                boards.put(new Position(H, rankCoordinate), new Pawn(color));
            } else {
                boards.put(new Position(A, rankCoordinate), Empty.create());
                boards.put(new Position(B, rankCoordinate), Empty.create());
                boards.put(new Position(C, rankCoordinate), Empty.create());
                boards.put(new Position(D, rankCoordinate), Empty.create());
                boards.put(new Position(E, rankCoordinate), Empty.create());
                boards.put(new Position(F, rankCoordinate), Empty.create());
                boards.put(new Position(G, rankCoordinate), Empty.create());
                boards.put(new Position(H, rankCoordinate), Empty.create());
            }
        }
        return new Board(boards);
    }
}
