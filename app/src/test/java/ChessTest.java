import chess.logic.classicGame.ClassicGame;
import common.models.Board;
import common.models.Coordinate;
import common.models.Game;
import common.results.MoveResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ChessTest {

    @Test
    void TestCheckMate() {
        Game game = ClassicGame.CreateGame();
        game.movePiece(new Coordinate(6, 2), new Coordinate(6, 3), game.getCurrentPlayer());
        game.movePiece(new Coordinate(5, 7), new Coordinate(5, 6), game.getCurrentPlayer());
        game.movePiece(new Coordinate(7, 2), new Coordinate(7, 4), game.getCurrentPlayer());
        MoveResult<Board, Boolean> g = game.movePiece(new Coordinate(4, 8), new Coordinate(8, 4), game.getCurrentPlayer());

        assertEquals("CheckMate", g.message());
    }
    @Test
    void TestIfPieceCanMoveOutsideBoard() {
        Game game = ClassicGame.CreateGame();
        MoveResult<Board, Boolean> g = game.movePiece(new Coordinate(1, 2), new Coordinate(1, 9), game.getCurrentPlayer());
        assertEquals("Common Rule unfollowed", g.message());
    }

    @Test
    void TestIfPieceCanMoveToSameSquare() {
        Game game = ClassicGame.CreateGame();
        MoveResult<Board, Boolean> g = game.movePiece(new Coordinate(1, 2), new Coordinate(1, 2), game.getCurrentPlayer());
        assertEquals("Common Rule unfollowed", g.message());
    }

}
