package day7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DoubleCustomSort {
    //Run this main to test sorting algorithm
    public static void main(String[] args) {
        //A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
        List<GameBid> gameBidList = Arrays.asList(new GameBid("32T3K 765"),
                        new GameBid("T55J5 684"),
                        new GameBid("KK677 28"),
                        new GameBid("KTJJT 220"),
                        new GameBid("QQQJA 483")
        );
        List<GameBid> sortedList = doubleCustomSort(gameBidList);

        for (GameBid gameBid : sortedList) {
            System.out.println("Sorted word: " + gameBid);
        }
    }
    static List<GameBid> doubleCustomSort(List<GameBid> gameBidList) {

        return gameBidList.stream()
                        .map(gameBid -> {
                            String sortedWord = DoubleCustomSort.customSort(gameBid.getWord());
                            gameBid.setWord(sortedWord);
                            return gameBid;
                        })
                        .sorted(Comparator.comparing(GameBid::getWord, DoubleCustomSort::compareWords))
                        .collect(Collectors.toList());
    }
    static int compareWords(String word1, String word2) {
        return Integer.compare(getOrder(word1.charAt(0)), getOrder(word2.charAt(0)));
    }
    static String customSort(String s) {
        Character[] charArray = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

        Arrays.sort(charArray, Comparator.comparingInt( DoubleCustomSort::getOrder));

        StringBuilder sortedStringBuilder = new StringBuilder();
        for (char c : charArray) {
            sortedStringBuilder.append(c);
        }

        return sortedStringBuilder.toString();
    }

    static int getOrder(char c) {
        switch (c) {
        case 'A':
            return 0;
        case 'K':
            return 1;
        case 'Q':
            return 2;
        case 'J':
            return 3;
        case 'T':
            return 4;
        case '9':
            return 5;
        case '8':
            return 6;
        case '7':
            return 7;
        case '6':
            return 8;
        case '5':
            return 9;
        case '4':
            return 10;
        case '3':
            return 11;
        case '2':
            return 12;
        default:
            throw new IllegalArgumentException("Invalid character: " + c);
        }
    }
}
