package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.exception.CannotStartException;
import domain.exception.GameNotStartException;
import domain.exception.ImmovableSamePositionException;
import domain.exception.InvalidMoveException;
import domain.piece.Position;
import view.OutputView;

import java.util.NoSuchElementException;

public class Move implements Command {

    @Override
    public MenuDto execute(String command, ChessGame game) {
        if (!game.isRunning()) {
            throw new GameNotStartException();
        }

        startMoveMenu(command, game);
        return new BoardDto(game.getBoard());
    }

    private void startMoveMenu(String command, ChessGame game) {
        try {
            String startPosition = command.split(" ")[1];
            String endPosition = command.split(" ")[2];
            game.move(Position.of(startPosition), Position.of(endPosition));
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("잘못된 위치 입력값입니다! (입력 : '" + command + "')");
        } catch (InvalidMoveException e) {
            System.out.println("해당 입력값으로 이동할 수 없습니다. (입력 : " + command + ")");
        } catch (ImmovableSamePositionException e) {
            System.out.println("현재 위치와 이동 위치가 같습니다.(입력 : " + command + ")");
        }
    }
}
