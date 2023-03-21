package chess.domain.position;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PiecesPosition {

    private final Map<Position, Piece> piecesPosition = new LinkedHashMap<>();

    public PiecesPosition() {
        setUpNobilityPiece(Rank.EIGHT, Camp.BLACK);
        setUpPawn(Rank.SEVEN, Camp.BLACK);

        setUpPawn(Rank.TWO, Camp.WHITE);
        setUpNobilityPiece(Rank.ONE, Camp.WHITE);
    }

    private void setUpNobilityPiece(Rank rank, Camp camp) {
        piecesPosition.put(Position.of(File.A, rank), new Rook(camp));
        piecesPosition.put(Position.of(File.B, rank), new Knight(camp));
        piecesPosition.put(Position.of(File.C, rank), new Bishop(camp));
        piecesPosition.put(Position.of(File.D, rank), new Queen(camp));
        piecesPosition.put(Position.of(File.E, rank), new King(camp));
        piecesPosition.put(Position.of(File.F, rank), new Bishop(camp));
        piecesPosition.put(Position.of(File.G, rank), new Knight(camp));
        piecesPosition.put(Position.of(File.H, rank), new Rook(camp));
    }

    private void setUpPawn(Rank rank, Camp camp) {
        for (File file : File.values()) {
            piecesPosition.put(Position.of(file, rank), new Pawn(camp));
        }
    }

    public Piece peekPiece(Position position) {
        return piecesPosition.get(position);
    }

    public boolean isPieceExist(Position position) {
        return piecesPosition.containsKey(position);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        piecesPosition.put(toPosition, piecesPosition.get(fromPosition));
        piecesPosition.remove(fromPosition);
    }

    public Map<Position, Piece> getPiecesPosition() {
        return piecesPosition;
    }
}
