package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class GraphPoet {
    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    // Constructor to build the graph from a file
    public GraphPoet(File corpus) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(corpus))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\W+");
                for (int i = 0; i < words.length - 1; i++) {
                    String word1 = words[i];
                    String word2 = words[i + 1];
                    graph.putIfAbsent(word1, new HashMap<>());
                    graph.get(word1).put(word2, graph.get(word1).getOrDefault(word2, 0) + 1);
                }
            }
        }
    }

    // Method to generate a poem based on input text
    public String poem(String input) {
        String[] words = input.toLowerCase().split("\\W+");
        StringBuilder poem = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            poem.append(word1).append(" ");

            String bridge = findBridgeWord(word1, word2);
            if (bridge != null) {
                poem.append(bridge).append(" ");
            }
        }
        poem.append(words[words.length - 1]);

        return poem.toString();
    }

    // Helper method to find a bridge word
    private String findBridgeWord(String word1, String word2) {
        if (!graph.containsKey(word1)) return null;
        String bridge = null;
        int maxWeight = 0;

        for (Map.Entry<String, Integer> entry : graph.get(word1).entrySet()) {
            String candidate = entry.getKey();
            int weight = entry.getValue();
            if (graph.containsKey(candidate) && graph.get(candidate).containsKey(word2)) {
                int totalWeight = weight + graph.get(candidate).get(word2);
                if (totalWeight > maxWeight) {
                    maxWeight = totalWeight;
                    bridge = candidate;
                }
            }
        }
        return bridge;
    }
}
