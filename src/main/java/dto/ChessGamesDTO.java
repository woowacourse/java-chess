package dto;

import java.util.ArrayList;

public class ChessGamesDTO {
    private final ArrayList<ChessGameDTO> chessGameDTOs;

    public ChessGamesDTO(ArrayList<ChessGameDTO> chessGameDTOs) {
        this.chessGameDTOs = chessGameDTOs;
    }

    public ArrayList<ChessGameDTO> getChessGameDTOs() {
        return chessGameDTOs;
    }
}
