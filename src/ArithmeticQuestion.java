/**
 * This class is a model class which is used to provide some methods for view class
 * @author Zhiping Yu    student number: 000822513   date: 2020.07.14
 */
public class ArithmeticQuestion {
    /* first number in the equation */
    private int number1;
    /* second number in the equation */
    private int number2;
    /* count how many correct answers in total */
    private int countRight;
    /* count how many wrong answers in total*/
    private int countWrong;
    /* count the correct rate in total */
    private double countRate;

    /**
     * Constructor
     * initialize instance variables, number 1 and number 2 are random numbers
     */
    public ArithmeticQuestion(){
        this.number1 = (int)(Math.random()*100);
        this.number2 = (int)(Math.random()*100);
    }

    /**
     * Add two numbers in the equation
     * @return the sum of two numbers
     */
    private int add(){
        return number1+number2;
    }

    /**
     * Check if the user input is correct. If it is correct, number of right answers plus one
     * @param result the sum user input
     * @return the number of correct answers
     */
    public int rightResult(int result){
        if (result == add()) {
            countRight++;
        }
        return countRight;
    }
    /**
     * Check if the user input is wrong. If it is wrong, number of wrong answers plus one
     * @param result the sum user input
     * @return the number of wrong answers
     */
    public int wrongResult(int result){
        if (result != add()) {
            countWrong++;

        };
        return countWrong;
    }

    /**
     * Calculate correct rate
     * @return correct rate
     */
    public double rateResult(){
        countRate = countRight/(countRight+countWrong+0.0);
        return countRate;
    }

    /**
     * Get first number in the equation
     * @return first number
     */
    public int getNumber1(){
        return number1;
    }

    /**
     * Get second number in the equation
     * @return second number
     */
    public int getNumber2(){
        return number2;
    }

    /**
     * Change first number based on passed in value
     * @param num1 first number of the equation
     */
    public void setNumber1(int num1){
        this.number1 = num1;
    }
    /**
     * Change second number based on passed in value
     * @param num2 second number of the equation
     */
    public void setNumber2(int num2){
        this.number2 = num2;
    }

    /**
     * Reset the number of correct answers to 0
     */
    public void resetRight(){
        this.countRight = 0;


    }

    /**
     * Reset the number of wrong answers to 0;
     */
    public void resetWrong(){
        this.countWrong = 0;
    }


    /**
     * Rest the correct rate to 0.0
     */
    public void resetRate(){this.countRate = 0.0;}

    }

