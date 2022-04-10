package chess.domain.piece.property;

import java.net.ProtocolFamily;

public enum Team {

    BLACK,
    WHITE;

    public Team changeTeam() {
        if (this == WHITE){
            return BLACK;
        }
        return WHITE;
    }
}
