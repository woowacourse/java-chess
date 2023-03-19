package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> board = new LinkedHashMap<>();

    public Map<Position, Piece> createBoard() {
        setUpNobilityPiece(Rank.EIGHT, Camp.BLACK);
        setUpPawn(Rank.SEVEN, Camp.BLACK);
        setUpBlank(Rank.SIX);
        setUpBlank(Rank.FIVE);
        setUpBlank(Rank.FOUR);
        setUpBlank(Rank.THREE);
        setUpPawn(Rank.TWO, Camp.WHITE);
        setUpNobilityPiece(Rank.ONE, Camp.WHITE);
        return board;
    }

    private void setUpNobilityPiece(Rank rank, Camp camp) {
        board.put(Position.of(File.A, rank), new Rook(camp));
        board.put(Position.of(File.B, rank), new Knight(camp));
        board.put(Position.of(File.C, rank), new Bishop(camp));
        board.put(Position.of(File.D, rank), new Queen(camp));
        board.put(Position.of(File.E, rank), new King(camp));
        board.put(Position.of(File.F, rank), new Bishop(camp));
        board.put(Position.of(File.G, rank), new Knight(camp));
        board.put(Position.of(File.H, rank), new Rook(camp));
    }

    private void setUpPawn(Rank rank, Camp camp) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Pawn(camp));
        }
    }

    private void setUpBlank(Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Empty());
        }
    }
}
