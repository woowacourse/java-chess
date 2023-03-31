package chess.domain.position;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ChessBoard {

    private final Map<Position, Piece> piecesPosition;

    public ChessBoard(Map<Position, Piece> piecesPosition) {
        this.piecesPosition = piecesPosition;
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

    public List<Piece> getPiecesOfCamp(Camp camp) {
        return piecesPosition.values()
                .stream()
                .filter(piece -> piece.isSameCamp(camp))
                .collect(Collectors.toList());
    }

    public List<Piece> getPiecesInFile(File file) {
        List<Position> positions = Stream.of(Rank.values())
                .map(rank -> Position.of(file, rank))
                .collect(Collectors.toList());

        return positions.stream()
                .filter(this::isPieceExist)
                .map(piecesPosition::get)
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> getPiecesPosition() {
        return piecesPosition;
    }
}
