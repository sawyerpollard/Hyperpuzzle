package com.sawyerpollard;

import com.sawyerpollard.akari.AkariGame;
import com.sawyerpollard.akari.AkariPanel;
import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridGame;
import com.sawyerpollard.gridgame.Solver;
import com.sawyerpollard.kakurasu.KakurasuGame;
import com.sawyerpollard.kakurasu.KakurasuPanel;
import com.sawyerpollard.skyscraper.SkyscraperGame;
import com.sawyerpollard.skyscraper.SkyscraperPanel;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GameLoader {
    private final String dataFolder;
    private final String fileExtension;
    private final HashMap<String, int[][][]> dataMap = new HashMap<>();
    private final HashMap<String, Integer> arrayIntervals = new HashMap<>();

    boolean[][] akariBarriers = new boolean[][]{
            {false, false, false, false, false, true, false},
            {true, true, false, false, true, true, false},
            {false, true, false, false, false, false, false},
            {false, false, false, false, false, false, false},
            {false, false, false, false, false, true, false},
            {false, true, true, false, false, true, true},
            {false, true, false, false, false, false, false}
    };

    int[][] akariAdjacentCounts = new int[][]{
            {-1, -1, -1, -1, -1, 0, -1},
            {0, 0, -1, -1, 2, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, 2, -1},
            {-1, 2, -1, -1, -1, 0, -1},
            {-1, 0, -1, -1, -1, -1, -1}
    };


    public GameLoader(String[] names, int[] arrayIntervals, String dataFolder, String fileExtension) {
        this.dataFolder = dataFolder;
        this.fileExtension = fileExtension;

        for (int i = 0; i < names.length; i++) {
            this.arrayIntervals.put(names[i], arrayIntervals[i]);
        }

        for (String name : names) {
            int interval = this.arrayIntervals.get(name);

            int[][] data = readData(name);

            ArrayList<int[][]> list = new ArrayList<>();
            for (int i = 0; i < data.length; i += interval) {
                list.add(Arrays.copyOfRange(data, i, i + interval));
            }

            dataMap.put(name, list.toArray(new int[0][0][0]));
        }
    }

    public Solver loadSolver(String name, GridGame game) {
        Solver solver;

        switch (name.toLowerCase()) {
            case "skyscraper":
                solver = new Solver(game, Arrays.copyOfRange(game.getPossibleValues(), 1, game.getPossibleValues().length), SkyscraperGame.BLANK);
                break;
            case "kakurasu":
                solver = new Solver(game, game.getPossibleValues(), KakurasuGame.BLANK);
                break;
            case "akari":
                solver = new Solver(game, game.getPossibleValues(), AkariGame.BLANK);
                break;
            default:
                throw new IllegalArgumentException("Game not found!");
        }

        return solver;
    }

    public GamePanel loadGamePanel(String name, GridGame game) {
        GamePanel gamePanel;

        switch (name.toLowerCase()) {
            case "skyscraper":
                gamePanel = new SkyscraperPanel((SkyscraperGame) game, Main.MIN_SIZE.width / 2 / game.numRows());
                break;
            case "kakurasu":
                gamePanel = new KakurasuPanel((KakurasuGame) game, Main.MIN_SIZE.width / 2 / game.numRows());
                break;
            case "akari":
                gamePanel = new AkariPanel((AkariGame) game, Main.MIN_SIZE.width / 2 / game.numRows());
                break;
            default:
                throw new IllegalArgumentException("Game not found!");
        }

        return gamePanel;
    }

    public GridGame loadGame(String name, int index) {
        GridGame game;
        int[][] data = getArrays(name, index);

        switch (name.toLowerCase()) {
            case "skyscraper":
                game = new SkyscraperGame(data[0], data[1], data[2], data[3]);
                break;
            case "kakurasu":
                game = new KakurasuGame(data[0], data[1]);
                break;
            case "akari":
                game = new AkariGame(akariBarriers, akariAdjacentCounts);
                break;
            default:
                throw new IllegalArgumentException("Game not found!");
        }

        return game;
    }

    public int[][] getArrays(String name, int index) {
        return dataMap.get(name)[index];
    }

    private int[][] readData(String name) {
        String path = Paths.get(dataFolder, name + fileExtension).toString();
        int[][] data = loadIntArrays(path);

        return data;

    }

    private int[][] loadIntArrays(String path) {
        Scanner lineScanner = new Scanner(getClass().getResourceAsStream(path));

        ArrayList<int[]> intArrays = new ArrayList<>();

        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();

            ArrayList<Integer> integers = new ArrayList<>();

            Scanner intScanner = new Scanner(line);
            while (intScanner.hasNextInt()) {
                integers.add(intScanner.nextInt());
            }

            intArrays.add(integerToIntArray(integers.toArray(new Integer[0])));
        }

        return intArrays.toArray(new int[0][0]);
    }

    private int[] integerToIntArray(Integer[] integerArray) {
        int[] intArray = new int[integerArray.length];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = integerArray[i];
        }

        return intArray;
    }
}
