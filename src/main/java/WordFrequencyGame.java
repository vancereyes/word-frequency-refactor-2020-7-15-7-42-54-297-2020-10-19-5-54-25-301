import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACE).length == 1) {
            return sentence + " 1";
        } else {

            try {
                String[] wordList = sentence.split(WHITE_SPACE);

                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String word : wordList) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfoList.add(wordInfo);
                }

                Map<String, List<WordInfo>> WordInfoMap = getListMap(wordInfoList);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : WordInfoMap.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }
                wordInfoList = list;

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo distinctWordInfo : wordInfoList) {
                    String s = String.format("%s %d", distinctWordInfo.getValue(), distinctWordInfo.getWordCount());
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> WordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       WordInfoMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!WordInfoMap.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                WordInfoMap.put(wordInfo.getValue(), arr);
            } else {
                WordInfoMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return WordInfoMap;
    }


}
