package chess.domain.chessGame;

import java.util.Arrays;

public enum ChessGameStateType {
    READY("ready", new ReadyChessGameState()),
    PLAYING("playing", new PlayingChessGameState()),
    END("end", new EndChessGameState());

    private final String name;
    private final ChessGameState chessGameState;

    ChessGameStateType(String name, ChessGameState chessGameState) {
        this.name = name;
        this.chessGameState = chessGameState;
    }

    public static ChessGameState findByName(String name) {
        ChessGameStateType chessGameStateType = Arrays.stream(ChessGameStateType.values())
                .filter(type -> type.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 체스 게임 상태가 아닙니다."));

        return chessGameStateType.chessGameState;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
