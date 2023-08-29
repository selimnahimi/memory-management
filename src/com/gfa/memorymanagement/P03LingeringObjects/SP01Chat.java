package com.gfa.memorymanagement.P03LingeringObjects;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SP01Chat {

  public static void main(String[] args) throws Exception {
    optimized();
  }

  static class Message {
    String sender;
    String content;
    Date sentDate;

    public Message(String sender, String content) {
      this.sender = sender;
      this.content = content;
      this.sentDate = new Date();
    }

    @Override
    public String toString() {
      return "[" + sentDate + "] " + sender + ": " + content;
    }

    @Override
    protected void finalize() {
      System.out.println("Message is garbage collected!");
    }
  }

  private static void optimized() throws Exception {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();

    String[] replies = loremIpsum.split("[ \n]");

    Scanner scanner = new Scanner(System.in);
    boolean chatting = true;
    List<Message> messageHistory = new ArrayList<>();

    System.out.print("Enter username: ");
    String username = scanner.nextLine();

    // CHAT
    for (int i = 0; i < 5000000; i++) {

      // System.out.print("Enter message: ");
      // String message = scanner.nextLine();
      String randomMessage = replies[(int) (Math.random() * replies.length)];
      messageHistory.add(new Message(username, randomMessage));

      String randomReply = replies[(int) (Math.random() * replies.length)];
      messageHistory.add(new Message("MrLorem", randomReply));
    }

    for (Message message : messageHistory) {
      // System.out.println(message);
    }

    System.out.println("Press enter to play hangman...");
    scanner.nextLine();

    messageHistory = new ArrayList<>();

    // HANGMAN
    boolean playing = true;
    int lives = 5;
    String wordToGuess = "greenfoxacademy";
    List<String> guessed = new ArrayList<>();

    while (playing) {
      System.gc();

      System.out.print("Enter a letter: ");
      String letter = scanner.nextLine();
      if (letter.length() == 0)
        continue;

      letter = letter.substring(0, 1);

      if (wordToGuess.contains(letter) && !guessed.contains(letter)) {
        guessed.add(letter);
      }

      StringBuilder output = new StringBuilder();
      for (int i = 0; i < wordToGuess.length(); i++) {
        String character = String.valueOf(wordToGuess.charAt(i));

        if (guessed.contains(character)) {
          output.append(character);
        } else {
          output.append("_");
        }
      }

      System.out.println(output);

      if (output.toString().equals(wordToGuess)) {
        System.out.println("You win!");
        playing = false;
      }
    }
  }
}
