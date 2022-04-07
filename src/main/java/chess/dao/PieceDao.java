package chess.dao;

import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.MoveDto;
import chess.dto.PieceDto;

import java.util.List;

public interface PieceDao {

    List<PieceDto> findPiecesByTeam(final Team team);

    void updatePiece(final MoveDto moveDto);

    void removePieceByCaptured(final MoveDto moveDto);

    void endPieces();

    void initializePieces(final Player player);
}
