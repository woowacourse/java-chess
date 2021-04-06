package chess.domain.dto;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WebBoardDto {
    private static final String imgTag = "<img class=\"chessboard\" src=\"/images/%s.png\" alt=\"\">";

    private final Board board;
    private final Team turn;
    private final boolean isEnd;
    private final Team winner;


    public WebBoardDto(Board board, Team turn, boolean isEnd, Team winner) {
        this.board = board;
        this.turn = turn;
        this.isEnd = isEnd;
        this.winner = winner;
    }

    private static String makePositionFormat(Rank rank, File file) {
        return file.getFile() + rank.getRank();
    }

    public Board getBoard() {
        return board;
    }

    public Map<String, Object> getWebBoard() {
        HashMap<String, Object> webMap = new HashMap<>();
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                String key = makePositionFormat(rank, file);
                Position position = Position.of(key);
                if (!board.hasPieceAt(position)) {
                    continue;
                }

                Piece piece = board.pieceAt(position);
                String value = piece.getTeam().name() + "-" + piece.getName();
                webMap.put(key, String.format(imgTag, value.toLowerCase(Locale.ROOT)));
            }
        }
        return webMap;
    }

    public Team getTurn() {
        return turn;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Team getWinner() {
        return winner;
    }
}
