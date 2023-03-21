package chess.domain;

import chess.domain.piece.Team;

public enum GameState {
    READY,
    BLACK,
    WHITE,
    END;

    public boolean isRunning(){
        return this == BLACK || this == WHITE;
    }

    public Team getCurrentTeam(){
        if(this == BLACK){
            return Team.BLACK;
        }
        if(this == WHITE){
            return Team.WHITE;
        }
        throw new UnsupportedOperationException("게임을 진행하고 있지 않습니다.");
    }

    public GameState convertTurn(){
        if(this == BLACK){
            return WHITE;
        }
        if(this == WHITE){
            return BLACK;
        }
        throw new UnsupportedOperationException("게임을 진행하고 있지 않습니다.");
    }
}
