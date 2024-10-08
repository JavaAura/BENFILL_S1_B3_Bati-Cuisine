package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
	private static Scanner scanner = new Scanner(System.in);

	public static Optional<Integer> getInteger(String label, String textToPrint, boolean optional) {
		while (true) {
			System.out.print(textToPrint + ": ");
			String userInput = scanner.nextLine().trim();

			if (optional && userInput.isEmpty()) {
				return Optional.empty();
			}

			try {
				return Optional.of(Integer.parseInt(userInput));
			} catch (NumberFormatException e) {
				if (!optional) {
					System.out.println(label + " cannot be empty and must be an integer. Please try again.");
				}
			}
		}
	}

	public static Boolean getConfirmation(String message) {
		String confirmation = getString("Confirmation", message, false).get();

		while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
			confirmation = getString("Confirmation", "Please Enter y or n", false).get();
		}
		if (confirmation.equalsIgnoreCase("y"))
			return true;
		else
			return false;
	}

	public static Optional<Double> getDouble(String label, String textToPrint, boolean optional) {
		while (true) {
			System.out.print(textToPrint + ": ");
			String userInput = scanner.nextLine().trim();

			if (optional && userInput.isEmpty()) {
				return Optional.empty();
			}

			try {
				return Optional.of(Double.parseDouble(userInput));
			} catch (NumberFormatException e) {
				if (!optional) {
					System.out.println(label + " cannot be empty and must be an integer. Please try again.");
				}
			}
		}
	}

	public static Optional<String> getString(String label, String textToPrint, boolean optional) {
		while (true) {
			System.out.print(textToPrint + ": ");
			String input = scanner.nextLine().trim();

			if (input.isEmpty() && !optional) {
				System.out.println(label + " cannot be empty. Please try again.");
			} else {
				return Optional.of(input);
			}
		}
	}

	public static Optional<LocalDate> getDate(String label, String textToPrint, boolean optional, boolean beforeToday) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		while (true) {
			System.out.print(textToPrint + " (format: yyyy-MM-dd): ");
			String input = scanner.nextLine().trim();

			if (input.isEmpty() && optional) {
				return Optional.empty();
			}

			try {
				LocalDate date = LocalDate.parse(input, formatter);
				if (date.isAfter(LocalDate.now()) && beforeToday)
					System.out.println("The date cannot be in the future. Please try again.");
				if (date.isBefore(LocalDate.now()) && !beforeToday)
					System.out.println("The date cannot be today or in the past. Please try again.");
				else
					return Optional.of(date);

			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in 'yyyy-MM-dd' format.");
			}
		}
	}

	public static Optional<String> getPhoneNumber(String label, String textToPrint, boolean optional) {
		String phoneNumberPattern = "^\\+?[0-9]{10,15}$"; // Adjust the pattern according to your requirements

		while (true) {
			System.out.print(textToPrint + ": ");
			String input = scanner.nextLine().trim();

			if (input.isEmpty() && optional) {
				return Optional.empty();
			}

			if (Pattern.matches(phoneNumberPattern, input)) {
				return Optional.of(input);
			} else {
				System.out.println(label + " must be a valid phone number. Please try again.");
			}
		}
	}
}
