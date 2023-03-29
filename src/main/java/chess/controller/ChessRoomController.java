package chess.controller;

import chess.dao.ChessRoomDao;
import chess.dto.ChessRoomDto;
import chess.dto.PlayerDto;

public class ChessRoomController {

    private final ChessRoomDao chessRoomDao = new ChessRoomDao();

    public ChessRoomDto handle(final PlayerDto playerDto) {
        chessRoomDao.createIfNotExist(playerDto);

        return chessRoomDao.findByPlayer(playerDto)
                .orElseThrow(() -> new RuntimeException("체스방 생성 실패"));
    }
}
