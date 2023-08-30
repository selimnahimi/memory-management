package com.gfa.memorymanagement.unoptimized.P03LingeringObjects;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Chat {

  public static void main(String[] args) throws Exception {
    unoptimized();
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
  }

  private static void unoptimized() throws Exception {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();

    String[] replies = loremIpsum.split("[ \n]");

    Scanner scanner = new Scanner(System.in);
    List<Message> messageHistory = new ArrayList<>();

    // CHAT
    for (int i = 0; i < 10000000; i++) {
      String randomMessage = replies[(int) (Math.random() * replies.length)];
      messageHistory.add(new Message("User1", randomMessage));

      String randomReply = replies[(int) (Math.random() * replies.length)];
      messageHistory.add(new Message("User2", randomReply));
    }

    System.out.println("Press enter to play hangman...");
    scanner.nextLine();

    // HANGMAN
    boolean playing = true;
    String wordToGuess = "greenfoxacademy";
    List<String> guessed = new ArrayList<>();

    while (playing) {
      System.out.print("Enter a letter: ");
      String letter = scanner.nextLine();
      if (letter.isEmpty())
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
