package chess.domain.generator;

import chess.domain.Position;
import chess.domain.piece.*;
import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadGeneratorImpl implements LoadGenerator {

    private final List<PieceDto> piecesDto;

    public LoadGeneratorImpl(List<PieceDto> piecesDto) {
        this.piecesDto = piecesDto;
    }

    @Override
    public List<Piece> generate() {
        final List<Piece> pieces = new ArrayList<>();
        for (PieceDto pieceDto : piecesDto) {
            pieces.add(findPiece(pieceDto));
        }
        return pieces;
    }

    private Piece findPiece(final PieceDto pieceDto) {
        final char name = Character.toLowerCase(pieceDto.getName().charAt(0));
        final Position position = Position.of(pieceDto.getPosition());
        final Map<Character, Piece> pieces = new HashMap<>();
        pieces.put('p', new Pawn(position));
        pieces.put('r', new Rook(position));
        pieces.put('n', new Knight(position));
        pieces.put('b', new Bishop(position));
        pieces.put('q', new Queen(position));
        pieces.put('k', new King(position));
        return pieces.get(name);
    }
}
