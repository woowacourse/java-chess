package chess.domain;

import chess.domain.piece.*;
import chess.dto.BoardDto;
import chess.view.WebUtil;

import java.util.*;
import java.util.stream.IntStream;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class Board {
    private final Map<Position, AbstractPiece> board;

    private Board(Map<Position, AbstractPiece> board) {
        this.board = board;
    }

    public static Board init() {
        Map<Position, AbstractPiece> board = new HashMap<>();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> {
                    board.put(new Position(new Coordinate(i), new Coordinate(8)), chessPieces(BLACK).get(i - 1));
                    board.put(new Position(new Coordinate(i), new Coordinate(1)), chessPieces(WHITE).get(i - 1));
                    board.put(new Position(new Coordinate(i), new Coordinate(7)), new Pawn(BLACK));
                    board.put(new Position(new Coordinate(i), new Coordinate(2)), new Pawn(WHITE));
                });
        return new Board(board);
    }

    public static Board load(Map<Position, AbstractPiece> board) {
        return new Board(board);
    }

    private static List<AbstractPiece> chessPieces(final Team team) {
        return Arrays.asList(
                new Rook(team), new Knight(team), new Bishop(team),
                new Queen(team), new King(team), new Bishop(team),
                new Knight(team), new Rook(team)
        );
    }

    public AbstractPiece at(final Position position) {
        return board.get(position);
    }

    public void move(Position source, Position target, AbstractPiece sourcePiece) {
        board.remove(source);
        board.put(target, sourcePiece);
    }

    public Map<Position, AbstractPiece> getBoard() {
        return board;
    }

    public List<BoardDto> toDto() {
        List<BoardDto> boardDtos = new ArrayList<>();
        board.forEach((position, piece) -> {
            BoardDto boardDto = new BoardDto();
            boardDto.setPieceName(piece.getName());
            boardDto.setTeam(piece.getTeam().name());
            boardDto.setPosition(WebUtil.positionParser(position));

            boardDtos.add(boardDto);
        });
        return boardDtos;
    }
}
