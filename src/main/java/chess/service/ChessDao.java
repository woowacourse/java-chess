package chess.service;

import chess.dto.MoveRequestDto;
import java.util.Map;

public interface ChessDao {
    Map<String, String> getBoardByGameId(String gameId);

    void move(MoveRequestDto moveRequestDto);

    int getTurnByGameId(String gameId);
}
