import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Frequenter {

    private FileReader fileReader;
    private Map<Character, Long> frequency = new HashMap<>();
    private TreeSet<Map.Entry<Character, Long>> sortedEntries = new TreeSet<>((e1, e2) -> {
        int res = Long.compare(e1.getValue(), e2.getValue());
        return (res==0)?(Character.compare(e1.getKey(), e2.getKey())):(res);
    });
    private long totalCount = 0;
    private int maxSignCount = 1;
    private long maxCount = 0;
    public Frequenter(FileReader fileReader){
        this.fileReader = fileReader;
        calcFrequency();
    }
    public void printStatistics(PrintStream out) {
        for(Map.Entry<Character, Long> entry:sortedEntries){
            out.printf("'%c':%"+maxSignCount+"d:%3d%%\n", entry.getKey(), entry.getValue(), entry.getValue()*100/totalCount);
        }
    }
    private void calcFrequency(){
        Scanner fileScanner = new Scanner(fileReader);
        while (fileScanner.hasNext()){
            for(Character s:fileScanner.next().toCharArray()){
                if(((s >= 'a') && (s <= 'z')) || ((s >= 'A') && (s <= 'Z'))){
                    if(frequency.containsKey(s)){
                        frequency.replace(s, frequency.get(s)+1);
                    }else {
                        frequency.put(s, 1L);
                    }
                    maxCount = Math.max(frequency.get(s), maxCount);
                    totalCount++;
                }
            }
        }
        int curSignCount = 0;
        long curCount = maxCount;
        while (curCount>0){
            curSignCount++;
            curCount/=10;
        }
        maxSignCount = curSignCount;
        sortedEntries.addAll(frequency.entrySet());
    }
}