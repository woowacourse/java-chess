package chess.controller;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDto {
    
    private final List<String> stringPieces;
    
    private BoardDto(final List<String> pieces) {
        this.stringPieces = pieces;
    }
    
    public static BoardDto create(Board board) {
        List<String> stringPieces = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            String stringRank = makeStringRank(board, rank);
            stringPieces.add(stringRank);
        }
        Collections.reverse(stringPieces);
        return new BoardDto(stringPieces);
    }
    
    private static String makeStringRank(final Board board, final Rank rank) {
        StringBuilder builder = new StringBuilder();
        for (Piece piece : board.getPiecesAt(rank)) {
            builder.append(PieceMapper.map(piece));
        }
        return builder.toString();
    }
    
    public List<String> getStringPieces() {
        return this.stringPieces;
    }
}
