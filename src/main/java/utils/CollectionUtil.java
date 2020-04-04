package utils;

import chess.dto.PieceDTO;
import chess.domain.piece.Placeable;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
            public static List<PieceDTO> convertMapToDTO(Map<Position, Placeable> pieces) {
                List<PieceDTO> piecesDTO = new ArrayList<>();

                for (Position position : pieces.keySet()) {
                    Placeable piece = pieces.get(position);

                    PieceDTO pieceDTO = new PieceDTO();
                    pieceDTO.setPosition(position);
                    pieceDTO.setPieceType(piece.getPieceType());
                    pieceDTO.setTeam(piece.getTeam());

                    piecesDTO.add(pieceDTO);
                }

        return piecesDTO;
    }
}
