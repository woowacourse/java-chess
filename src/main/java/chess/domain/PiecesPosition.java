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

public final class PiecesPosition {

    private final Map<Position, Piece> piecesPosition = new LinkedHashMap<>();

    public PiecesPosition() {
        setUpNobilityPiece(Rank.EIGHT, Camp.BLACK);
        setUpPawn(Rank.SEVEN, Camp.BLACK);

        setUpBlank(Rank.SIX);
        setUpBlank(Rank.FIVE);
        setUpBlank(Rank.FOUR);
        setUpBlank(Rank.THREE);

        setUpPawn(Rank.TWO, Camp.WHITE);
        setUpNobilityPiece(Rank.ONE, Camp.WHITE);
    }

    private void setUpNobilityPiece(Rank rank, Camp camp) {
        piecesPosition.put(new Position(File.A, rank), new Rook(camp));
        piecesPosition.put(new Position(File.B, rank), new Knight(camp));
        piecesPosition.put(new Position(File.C, rank), new Bishop(camp));
        piecesPosition.put(new Position(File.D, rank), new Queen(camp));
        piecesPosition.put(new Position(File.E, rank), new King(camp));
        piecesPosition.put(new Position(File.F, rank), new Bishop(camp));
        piecesPosition.put(new Position(File.G, rank), new Knight(camp));
        piecesPosition.put(new Position(File.H, rank), new Rook(camp));
    }

    private void setUpPawn(Rank rank, Camp camp) {
        for (File file : File.values()) {
            piecesPosition.put(new Position(file, rank), new Pawn(camp));
        }
    }

    private void setUpBlank(Rank rank) {
        for (File file : File.values()) {
            piecesPosition.put(new Position(file, rank), new Empty());
        }
    }

    public Piece choicePiece(Position position) {
        return piecesPosition.get(position);
    }

    public Map<Position, Piece> getPiecesPosition() {
        return piecesPosition;
    }
}
