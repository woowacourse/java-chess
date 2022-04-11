package chess.dao;

import chess.service.dto.ChessGameDto;
import chess.service.dto.GamesDto;
import chess.service.dto.StatusDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryGameDao implements GameDao {

    private static final String DEFAULT_STATUS = "EMPTY";
    private static final String DEFAULT_TURN = "WHITE";
    private final Map<Integer, ChessGameDto> gameTable = new HashMap<>();
    private int id = 1;

    @Override
    public void createGame(String name) {
        gameTable.put(id++, new ChessGameDto(1, name, DEFAULT_STATUS, DEFAULT_TURN));
    }

    @Override
    public void update(ChessGameDto dto) {
        gameTable.put(dto.getId(), dto);
    }

    @Override
    public ChessGameDto findById(int id) {
        return gameTable.get(id);
    }

    @Override
    public void updateStatus(StatusDto statusDto, int id) {
        String replaceStatus = statusDto.getStatus();
        ChessGameDto chessGameDto = gameTable.get(id);
        ChessGameDto replaceChessGameDto = new ChessGameDto(chessGameDto.getId(), chessGameDto.getName(), replaceStatus,
                chessGameDto.getTurn());
        gameTable.put(1, replaceChessGameDto);
    }

    @Override
    public GamesDto findAll() {
        return new GamesDto(new ArrayList<>(gameTable.values()));
    }

    public Map<Integer, ChessGameDto> getGameTable() {
        return gameTable;
    }
}
