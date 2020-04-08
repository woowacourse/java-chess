package utils;

import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assembler {
    public static List<PieceDto> convertMapToDTO(Map<Position, Placeable> pieces) {
        List<PieceDto> piecesDTO = new ArrayList<>();

        for (Position position : pieces.keySet()) {
            Placeable piece = pieces.get(position);

            PieceDto pieceDTO = new PieceDto(position, piece.getTeam(), piece.getPieceType());
            piecesDTO.add(pieceDTO);
        }

        return piecesDTO;
    }
}
