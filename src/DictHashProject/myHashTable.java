package DictHashProject;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public  class myHashTable  {

    private LinkedList<String>[] table;
    private static final int ResizeEdge = 9000;
    private int size;
    private int wordCount;

    public myHashTable() {
        table = new LinkedList[1]; // Başlangıçta kapasite 1
        table[0] = new LinkedList<>();
        size = 0;
        wordCount = 0;
    }


    public int hash(String word) {
        return Math.abs(word.hashCode() % table.length);
    }


    public void addWord(String word) {
        int index = hash(word);
        if (table[index] == null) {
            table[index] = new LinkedList<>(); // LinkedList oluşturulması
        }


        if (!table[index].contains(word)) {
            table[index].add(word);
            size++;
            wordCount++;

            if (wordCount >= ResizeEdge) {
                resizeTable();
                wordCount = 0;
            }
        }
    }


    public boolean containsWord(String word) {
        int index = hash(word);
        if (table == null || table[index] == null) {
            return false;
        }

        return table[index].contains(word);
    }


    public void removeWord(String word) {
        int index = hash(word);
        table[index].remove(word);
        size--;
    }
    private void resizeTable() {
        int newCapacity = table.length * 2;
        LinkedList<String>[] newTable = new LinkedList[newCapacity];
        size = 0; // Yeni tabloda eleman sayısını sıfırla

        for (LinkedList<String> list : table) {
            if (list != null) {
                for (String word : list) {
                    int newIndex = Math.abs(word.hashCode() % newCapacity);
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new LinkedList<>();
                    }
                    newTable[newIndex].add(word);
                    size++;
                }
            }
        }

        table = newTable;
    }
}
