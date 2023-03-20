package chess.dto;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatusDto {

    private final List<String> gameStatus;

    private GameStatusDto(List<String> gameStatus) {
        this.gameStatus = gameStatus;
    }

    // TODO: 죄송합니다. 심각함ㅠㅠ
    public static GameStatusDto from(final Board board) {
        Map<Square, Piece> domainBoard = board.getBoard();
        List<String> gameStatus = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            char rank = (char) ('8' - i);
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                char file = (char) ('a' + j);
                Square now = Square.of(File.from(file), Rank.from(rank));
                if (domainBoard.containsKey(now)) {
                    Piece piece = domainBoard.get(now);
                    String name = piece.getName();
                    if (piece.getColor() == Color.BLACK) {
                        name = name.toUpperCase();
                    }
                    row.append(name);
                    continue;
                }
                row.append(".");
            }
            gameStatus.add(row.toString());
        }
        return new GameStatusDto(gameStatus);
    }

    public List<String> getGameStatus() {
        return new ArrayList<>(gameStatus);
    }
}
