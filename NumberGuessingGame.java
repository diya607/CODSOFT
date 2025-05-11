import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int score = 0;

        System.out.println("Welcome to My Number Guessing Game Project");

        String playAgain;

        do {
            int numberToGuess = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean isGuessed = false;

            System.out.println("\nI have selected a number between " + lowerBound + " and " + upperBound);
            System.out.println("You have " + maxAttempts + " attempts to guess the number");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    score += (maxAttempts - attempts + 1) * 10;
                    isGuessed = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }
            }

            if (!isGuessed) {
                System.out.println("Oops! You've used all your attempts.");
                System.out.println("The correct number was: " + numberToGuess);
            }

            System.out.println("Your current score is: " + score);

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thank you for playing my Number Guessing Game.");
        System.out.println("Your Final Score is: " + score);
    }
}

