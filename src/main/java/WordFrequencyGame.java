import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACE).length == 1) {
            return sentence + " 1";
        } else {

            try {
                String[] wordList = sentence.split(WHITE_SPACE);

                List<WordInfo> wordInfoList = getWordInfos(wordList);

                Map<String, List<WordInfo>> WordInfoMap = getListMap(wordInfoList);

                List<WordInfo> distinctWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : WordInfoMap.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    distinctWordInfos.add(wordInfo);
                }
                wordInfoList = distinctWordInfos;

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return getWordFrequency(wordInfoList);
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private String getWordFrequency(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo distinctWordInfo : wordInfoList) {
            String s = String.format("%s %d", distinctWordInfo.getValue(), distinctWordInfo.getWordCount());
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<WordInfo> getWordInfos(String[] wordList) {
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : wordList) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }
        return wordInfoList;
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> WordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {

            if (!WordInfoMap.containsKey(wordInfo.getValue())) {
                ArrayList wordList = new ArrayList<>();
                wordList.add(wordInfo);
                WordInfoMap.put(wordInfo.getValue(), wordList);
            } else {
                WordInfoMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return WordInfoMap;
    }


}
