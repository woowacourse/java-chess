package chess.controller;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDto {
    
    private final List<String> stringPieces;
    
    private BoardDto(final List<String> pieces) {
        this.stringPieces = pieces;
    }
    
    public static BoardDto create(ChessGame chessGame) {
        List<String> stringPieces = new ArrayList<>();
        List<List<Piece>> boardPieces = chessGame.getPieces();
        for (List<Piece> rankPieces : boardPieces) {
            StringBuilder rank = new StringBuilder();
            for (Piece piece : rankPieces) {
                rank.append(PieceMapper.map(piece));
            }
            stringPieces.add(rank.toString());
        }
        Collections.reverse(stringPieces);
        return new BoardDto(stringPieces);
    }
    
    
    public List<String> getStringPieces() {
        return stringPieces;
    }
}
