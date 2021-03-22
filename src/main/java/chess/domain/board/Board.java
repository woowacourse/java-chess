package chess.domain.board;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackPiece;
import chess.domain.piece.white.WhitePiece;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Board {
    private static final int ROW = 8;
    private static final int COLUMN = 8;

    private final Pieces<BlackPiece> blackPieces;
    private final Pieces<WhitePiece> whitePieces;

    public Board(List<ChessPiece> pieces) {
        Map<Boolean, List<ChessPiece>> partitionedPieces = pieces.stream()
                .collect(partitioningBy(piece -> piece instanceof BlackPiece));

        blackPieces = new Pieces(partitionedPieces.get(true));
        whitePieces = new Pieces(partitionedPieces.get(false));
    }

    public Board(Pieces<BlackPiece> blackPieces, Pieces<WhitePiece> whitePieces) {
        this.blackPieces = blackPieces;
        this.whitePieces = whitePieces;
    }

    public void moveBlackPiece(Position source, Position target) {
        blackPieces.movePiece(source, target, this);

    }

    public void moveWhitePiece(Position source, Position target) {
        whitePieces.movePiece(source, target, this);
    }

    public void catchBlackPiece() {
        for (Map.Entry<Position, Long> positionLongEntry : findDuplicatePosition()) {
            blackPieces.removeIf(chessPiece -> chessPiece.isSamePosition(positionLongEntry.getKey()));
        }
    }

    public void catchWhitePiece() {
        for (Map.Entry<Position, Long> positionLongEntry : findDuplicatePosition()) {
            whitePieces.removeIf(chessPiece -> chessPiece.isSamePosition(positionLongEntry.getKey()));
        }
    }

    private List<Map.Entry<Position, Long>> findDuplicatePosition() {
        return Stream.of(blackPieces.getPieces().stream(), whitePieces.getPieces().stream())
                .flatMap(Function.identity())
                .collect(groupingBy(piece -> new Position(piece.getRow(), piece.getColumn()), counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .collect(toList());
    }

    public boolean isKingCaught() {
        return !(blackPieces.isKingExist() && whitePieces.isKingExist());
    }

    public static int getRow() {
        return ROW;
    }

    public static int getColumn() {
        return COLUMN;
    }

    public List<ChessPiece> getBlackPieces() {
        return blackPieces.getPieces();
    }

    public List<ChessPiece> getWhitePieces() {
        return whitePieces.getPieces();
    }

    public List<ChessPiece> getAllPieces() {
        return Stream.of(blackPieces.getPieces().stream(), whitePieces.getPieces().stream())
                .flatMap(Function.identity())
                .collect(toList());
    }

    public double getWhiteScore() {
        return whitePieces.getScore();
    }

    public double getBlackScore() {
        return blackPieces.getScore();
    }
}
