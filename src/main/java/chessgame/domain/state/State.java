package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;

/**
 *                      +-----+-----+
 *                      |   Ready   |-----> ---------------
 *                      +-----+-----+                     |
 *                          |                            |
 *                          |                            |
 *                          |                            |
 *                          |                            |
 *                          |                            |
 *                          v                            |
 *                   +-------+----+                      |
 *                   |    White   | ---> ----------------v
 *                   +----+-------+                      |
 *                         ^                             |
 *                         |                             |
 *                         v                             |
 *                   +-------+----+                      |
 *                   |    Black   | ---> ----------------|
 *                   +----+-------+                      v
 *                                                    +-----+------+
 *                                                   |    End     |
 *                                                   +------------+
 */
public interface State {
    boolean isEnd();

    State run(Command command, Board board);
}
