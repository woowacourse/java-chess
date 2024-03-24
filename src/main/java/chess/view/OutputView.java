package chess.view;

import chess.domain.Position;
import chess.domain.Status;
import chess.domain.piece.character.Character;
import chess.dto.BoardStatusDto;
import java.util.Map;

public class OutputView {
    public static final String EMPTY_POSITION = ".";

    public static void printChessBoard(Map<Position, Character> piecesCharacter) {
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                System.out.print(pieceToString(piecesCharacter, Position.of(i, j)));
            }
            System.out.println();
        }
        System.out.println();
    }

    private static String pieceToString(Map<Position, Character> piecesCharacter, Position position) {
        if (piecesCharacter.containsKey(position)) {
            Character character = piecesCharacter.get(position);
            return CharacterViewer.convertToString(character);
        }
        return EMPTY_POSITION;
    }

    public static void printGameStatus(BoardStatusDto boardStatusDto) {
        printChessBoard(boardStatusDto.board());
        printStatus(boardStatusDto.status());
    }

    private static void printStatus(Status status) {
        switch (status) {
            case CHECKMATE -> System.out.println("체크메이트!");
            case CHECK -> System.out.println("체크!");
        }
    }
}
