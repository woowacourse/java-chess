package chess.controller.dto;

import chess.domain.BoardProvider;
import chess.domain.piece.PieceProvider;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputRenderer {
    private static final Map<PieceType, String> PIECE_TO_STRING = new EnumMap<>(PieceType.class);
    private static final Map<Team, String> TEAM_TO_STRING = new EnumMap<>(Team.class);

    static {
        PIECE_TO_STRING.put(PieceType.EMPTY, ".");
        PIECE_TO_STRING.put(PieceType.KING, "K");
        PIECE_TO_STRING.put(PieceType.QUEEN, "Q");
        PIECE_TO_STRING.put(PieceType.ROOK, "R");
        PIECE_TO_STRING.put(PieceType.KNIGHT, "N");
        PIECE_TO_STRING.put(PieceType.BISHOP, "B");
        PIECE_TO_STRING.put(PieceType.PAWN, "P");

        TEAM_TO_STRING.put(Team.BLACK, "검정색");
        TEAM_TO_STRING.put(Team.WHITE, "하얀색");
        TEAM_TO_STRING.put(Team.EMPTY, " ");
    }

    public static BoardDto toBoardDto(final BoardProvider board) {
        return new BoardDto(stringifyBoard(board));
    }

    private static List<List<String>> stringifyBoard(BoardProvider board) {
        List<List<String>> boardDto = new ArrayList<>();
        int lineSize = board.getLineSize();

        List<PieceProvider> pieces = new ArrayList<>(board.getPieces());
        for (int i = 0; i < lineSize; i++) {
            int startPoint = i * lineSize;
            List<PieceProvider> line = new ArrayList<>(pieces.subList(startPoint, startPoint + lineSize));
            boardDto.add(stringifyLine(line));
        }
        return boardDto;
    }

    private static List<String> stringifyLine(final List<PieceProvider> line) {
        return line.stream()
                .map(OutputRenderer::stringifySign)
                .collect(Collectors.toList());
    }

    private static String stringifySign(final PieceProvider piece) {
        String sign = PIECE_TO_STRING.get(piece.getPieceType());
        if (piece.getTeam() == Team.WHITE) {
            sign = sign.toLowerCase();
        }
        return sign;
    }

    public static StatusDto toStatusDto(final Team team, final double score) {
        return new StatusDto(TEAM_TO_STRING.get(team), Double.toString(score));
    }

    public static TeamDto toTeamDto(final Team team) {
        return new TeamDto((TEAM_TO_STRING.get(team)));
    }
}
