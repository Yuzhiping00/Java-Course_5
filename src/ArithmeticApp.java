import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import static javafx.scene.paint.Color.*;

/**
 * The class is a view class, which displays all graphical components. User can input answers to check if
 * his/ her scores and he or she can get feedback. If user is not satisfied with the scores, he/she can
 * reset everything and do the math questions again
 * @author Zhiping Yu   student number: 000822513   date: 2020.07.14
 *
 */
public class ArithmeticApp extends Application {
    /* declare an ArithmeticQuestion object */
    ArithmeticQuestion mathQuestion;
    /* view components */
    Label titleLabel; // title label
    Label rightLabel; // a label with text right
    Label wrongLabel; // a label with text wrong
    Label corRateLabel; // a label with text correct rate
    Label leftNumber; // first number in the equation
    Label rightNumber; // second number in the equation
    Label feedbackLabel; // feedback
    Button okButton; // ok button
    //Button mathButton; // math button
    Button resetButton; // reset button
    Button feedbackButton; // feedback button
    TextField numOfRight; // a field for record the number of correct answers
    TextField numOfWrong; // a field for record the number of wrong answers
    TextField correctRate; // a field for record the correct rate
    TextField resultField; // a field for user to input equation result
    int countRight; // the number of correct answers
    int countWrong; // the number of wrong answers
    double countRate; // the correct rate value



    /**
     * Reset the values in the text fields to the original state, reset number of right, wrong answers and
     * correct rate to 0,0 or 0.00%, then display new equation
     *
     * @param actionEvent triggers some relevant events
     */
    private void resetButton(ActionEvent actionEvent) {

        /* reset the values in the all text fields to the original state */
        numOfRight.setText("0");
        numOfWrong.setText("0");
        correctRate.setText("0%");
        resultField.setText(""); // answer part
        feedbackLabel.setText("");// feedback part
        /* randomly change the numbers in the equation and display the equation on the view*/
        mathQuestion.setNumber1((int) (Math.random() * 100));
        mathQuestion.setNumber2((int) (Math.random() * 100));
        leftNumber.setText(mathQuestion.getNumber1() + " + ");
        rightNumber.setText(mathQuestion.getNumber2() + " = ");
        resultField.setText("");
        /* set the number of right, wrong answers, correct rate field to default values */
        mathQuestion.resetRight();
        mathQuestion.resetWrong();
        mathQuestion.resetRate();
        resultField.requestFocus();
    }

    /**
     * Get user's result  and calculate how many answers are right, wrong and correct
     * rate. In addition, after showing the relevant information, change two numbers in the equation
     * randomly and wait for user to press any buttons on the view
     *
     * @param actionEvent triggers some relevant events
     */
    private void OkButton(ActionEvent actionEvent) {

        String value = resultField.getText(); // get the result which user entered
        int number = Integer.parseInt(value); // convert that result from String to integer
        countRight = mathQuestion.rightResult(number); // calculate how many right answers
        numOfRight.setText(String.valueOf(countRight)); // convert number of right answers to String
        countWrong = mathQuestion.wrongResult(number); // calculate how many wrong answers
        numOfWrong.setText(String.valueOf(countWrong));// convert number of wrong answers to String
        countRate = mathQuestion.rateResult(); // calculate the the correct rate
        correctRate.setText(String.format("%.2f", countRate * 100) + "%"); // convert correct rate to String
        mathQuestion.setNumber1((int) (Math.random() * 100)); // set left number in the equation
        mathQuestion.setNumber2((int) (Math.random() * 100)); // set right number in the equation
        /* convert left and right numbers into String and display them on the view */
        leftNumber.setText(mathQuestion.getNumber1() + " + ");
        rightNumber.setText(mathQuestion.getNumber2() + " = ");
        resultField.setText(""); // set result field to empty
        resultField.requestFocus();

    }

    /**
     * Get different feedback based on scores
     *
     * @param actionEvent triggers some events
     */
    private void feedbackButton(ActionEvent actionEvent) {
        if (countRight < countWrong) {
            feedbackLabel.setStyle("-fx-text-fill:red");
            feedbackLabel.setText("More practice is needed.");
        } else if (countRight == countWrong) {
            feedbackLabel.setStyle("-fx-text-fill:blue");
            feedbackLabel.setText("Your score is Ok.");
        } else {
            feedbackLabel.setStyle("-fx-text-fill:green");
            feedbackLabel.setText("Well done!");
        }
        resultField.requestFocus();
    }


    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage he FX stage to draw on
     * @throws Exception throws an Exception
     */
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene sc = new Scene(root,650,400);
        stage.setTitle("Compute!"); // set stage title
        stage.setScene(sc);
        Canvas canvas = new Canvas(650,400);
        GraphicsContext gc = canvas.getGraphicsContext2D(); // receive a GraphicsContext object

        // create object of model and view components which are with default values
        mathQuestion = new ArithmeticQuestion();
        okButton = new Button("OK");
        resetButton = new Button("Reset");
        feedbackButton = new Button("Feedback");

        titleLabel = new Label("A r i t h m e t i c");
        rightLabel = new Label("Right");
        wrongLabel = new Label("Wrong");
        corRateLabel = new Label("Correct Rate");

        numOfRight = new TextField("0");
        numOfWrong = new TextField("0");
        correctRate = new TextField("0%");
        resultField = new TextField("");
        int num1 = mathQuestion.getNumber1(); // get first number from model
        int num2 = mathQuestion.getNumber2(); // get second number form model
        /* display the equation */
        leftNumber = new Label(num1+" + ");
        rightNumber = new Label(num2+" = ");
        feedbackLabel = new Label("");
        /* Add view components to the root */
        root.getChildren().addAll(canvas,okButton,resetButton,feedbackButton,titleLabel,rightLabel,
                wrongLabel,corRateLabel,numOfRight,numOfWrong,correctRate,resultField,leftNumber,rightNumber,
                feedbackLabel);
        /* configure all the view components */
        titleLabel.relocate(80,5);// title
        titleLabel.setFont(new Font("Arial", 50));
        titleLabel.setTextFill(HOTPINK);
        okButton.relocate(210,320);
        okButton.setMinWidth(80);
        okButton.setStyle("-fx-background-color: skyblue; -fx-text-fill: purple;");
        okButton.setFont(new Font("Geneva",25));
        resetButton.relocate(540,300);
        resetButton.setMinWidth(40);
        feedbackButton.relocate(530,350);
        feedbackButton.setMinWidth(40);
        rightLabel.relocate(500,10);
        numOfRight.relocate(570,10);
        numOfRight.setPrefColumnCount(3);
        numOfRight.setAlignment(Pos.CENTER);
        wrongLabel.relocate(500,40);
        numOfWrong.relocate(570,40);
        numOfWrong.setPrefColumnCount(3);
        numOfWrong.setAlignment(Pos.CENTER);
        corRateLabel.relocate(500,70);
        correctRate.relocate(570,70);
        correctRate.setPrefColumnCount(5);
        correctRate.setAlignment(Pos.CENTER);
        gc.setFill(LIGHTBLUE);
        gc.fillRect(480,2, 218, 396);
        gc.setFill(IVORY);
        gc.fillRect(0,0,480,396);
        leftNumber.relocate(100,150);
        leftNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        rightNumber.relocate(180,150);
        rightNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        resultField.relocate(270, 150);
        resultField.setPrefColumnCount(2); // ask use to input result
        resultField.setPrefHeight(40);
        resultField.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        feedbackLabel.relocate(110,230);
        feedbackLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        /* Add Listeners and do final setup */
        resultField.requestFocus(); // move cursor to the field
        okButton.setOnAction(this::OkButton);
        resetButton.setOnAction(this::resetButton);
        feedbackButton.setOnAction(this::feedbackButton);
        /* show the stage */
        stage.show();
    }
    /**
     * The actual main method that launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
