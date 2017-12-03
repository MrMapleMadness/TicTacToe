import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.Point;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Output extends Application {

    private Stage primaryStage;
    private final Label title = new Label("Tic Tac Toe\nAngus Clinch");
    private final Image titleImage = new Image("img/fivium-logo-grey-text.png");
    private final GridPane grid = new GridPane();
    private boolean textInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tic Tac Toe");

        //Create the title page and wait
        Scene titleScene = createTitleScene();
        titleScene.getStylesheets().add("css/title.css");

        primaryStage.setScene(titleScene);
        primaryStage.show();
    }

    private Scene createTitleScene() {
        BorderPane titlePane = new BorderPane();

        title.getStyleClass().add("outline");

        titlePane.setCenter(title);
        titlePane.setBottom(new ImageView(titleImage));

        Scene created = new Scene(titlePane, 500, 500);
        created.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> runGame());

        return created;
    }

    private void runGame() {
        new Game(new Output(true));
    }

    public Output(boolean textInput) {
        this.textInput = textInput;
    }

    String[] setNewGame(){
        if (textInput) {
            System.out.println("==== WELCOME TO eCase and Spire ====");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Please enter the name for Player One (Crosses)");
            String p1 = scanner.next();

            System.out.println("Please enter the name for Player Two (Naughts)");
            String p2 = scanner.next();

            System.out.println("Thank you, now let's play!");

            return new String[]{p1 , p2};
        } else {
            //JavaFX
            return new String[] {"eCase" , "SPIRE"};
        }
    }

    Point getInput(int DIMENSIONS) {
        if (textInput) {
            int row, col;
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Please enter a row from 1 to " + DIMENSIONS);
                row = scanner.nextInt() - 1;

                System.out.println("Please enter a column from 1 to " + DIMENSIONS);
                col = scanner.nextInt() - 1;

                return new Point(row, col);

            } catch (InputMismatchException e) {
                displayMessage("Please only enter a number");
                return null;
            }
        } else {
            //JavaFX


            return null;
        }
    }

    void printBoard(Piece[][] tiles) {
        if (textInput) {
            System.out.println("-------------");
            System.out.println("| " + tiles[0][0].getPieceCode() + " | " + tiles[0][1].getPieceCode() + " | " + tiles[0][2].getPieceCode() + " |");
            System.out.println("|---|---|---|");
            System.out.println("| " + tiles[1][0].getPieceCode() + " | " + tiles[1][1].getPieceCode() + " | " + tiles[1][2].getPieceCode() + " |");
            System.out.println("|---|---|---|");
            System.out.println("| " + tiles[2][0].getPieceCode() + " | " + tiles[2][1].getPieceCode() + " | " + tiles[2][2].getPieceCode() + " |");
            System.out.println("-------------");
        } else {
            //JavaFX
            GridPane pane = new GridPane();

            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    ImageView imageView = new ImageView(tiles[i][j].getImage());
                    pane.add(imageView, i, j);
                }
            }

            Scene gameScene = new Scene(pane, 500, 500);
            primaryStage.setScene(gameScene);
        }
    }

    boolean winner(Player currentPlayer, Player player1, Player player2) {
        if (textInput) {
            Scanner sc = new Scanner(System.in);
            currentPlayer.winsGame();

            System.out.println("Congratulations " + currentPlayer.getName() + "!");
            System.out.print(player1.getName() + ": " + player1.getScore() + " | ");
            System.out.println(player2.getName() + ": " + player2.getScore());
            System.out.println("Would you like to play again?");

            while (true) {
                System.out.println("Yes | No");
                String input;

                try {
                    input = sc.next().toLowerCase().substring(0, 1);
                } catch (StringIndexOutOfBoundsException e) {
                    input = "failed";
                }

                switch (input) {
                    case "y":
                        return true;
                    case "n":
                        System.out.println("Thanks for playing!");
                        return false;
                    default:
                        System.out.println("Sorry, I didn't understand that. Please try again.");
                        break;
                }
            }
        } else {
            //JavaFX
            return true;
        }
    }


    void displayMessage(String message) {
        if (textInput) {
            System.out.println(message);
        } else {
            //JavaFX
            return;
        }
    }
}
