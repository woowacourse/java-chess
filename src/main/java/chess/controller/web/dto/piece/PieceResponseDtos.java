package chess.controller.web.dto.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceResponseDtos {

    private final List<PieceResponseDto> pieceResponseDtos;

    public PieceResponseDtos(final List<PieceResponseDto> pieceResponseDtos) {
        this.pieceResponseDtos = pieceResponseDtos;
    }

    public Board toBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceResponseDto pieceResponseDto : pieceResponseDtos) {
            pieces.put(pieceResponseDto.toPosition(), pieceResponseDto.toPiece());
        }
        return new Board(pieces);
    }

    public List<PieceResponseDto> getPieceResponseDtos() {
        return pieceResponseDtos;
    }
}
