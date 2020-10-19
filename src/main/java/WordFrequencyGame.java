import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACE).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<String> words = Arrays.asList(sentence.split(WHITE_SPACE));
                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : new HashSet<>(words)) {
                    int count = Collections.frequency(words, word);
                    wordInfos.add(new WordInfo(word, count));
                }

                wordInfos.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return getWordFrequency(wordInfos);

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


}
