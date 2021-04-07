package chess.dto;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Pieces;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PiecesDTO {
    private final List<PieceDTO> pieceDTOs;

    private PiecesDTO(final List<PieceDTO> pieceDTOs) {
        this.pieceDTOs = pieceDTOs;
    }

    public static PiecesDTO create(final Board board) {
        Map<Team, Pieces> piecesByTeam = board.toMap();
        Pieces blackTeamPieces = piecesByTeam.get(Team.BLACK);
        Pieces whiteTeamPieces = piecesByTeam.get(Team.WHITE);
        List<PieceDTO> pieceDTOs = new ArrayList<>();
        convert(pieceDTOs, blackTeamPieces, Team.BLACK);
        convert(pieceDTOs, whiteTeamPieces, Team.WHITE);
        return new PiecesDTO(pieceDTOs);
    }

    private static void convert(List<PieceDTO> list, Pieces pieces, Team team) {
        list.addAll(pieces.toList().stream()
                .map(piece -> {
                    Position position = piece.position();
                    return new PieceDTO(team.name(), piece.initial(),
                            Col.initial(position.col()) + Row.initial(position.row()));
                }).collect(Collectors.toList()));
    }

    public List<PieceDTO> toList() {
        return pieceDTOs;
    }
}
