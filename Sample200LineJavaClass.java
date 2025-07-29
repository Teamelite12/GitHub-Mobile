package com.example.sample;

import java.util.*;

public class Sample200LineJavaClass {

    private Map<Integer, String> dataMap;
    private List<String> dataList;
    private Set<String> dataSet;
    private int counter;

    public Sample200LineJavaClass() {
        dataMap = new HashMap<>();
        dataList = new ArrayList<>();
        dataSet = new HashSet<>();
        counter = 0;
    }

    public void addData(int id, String data) {
        dataMap.put(id, data);
        dataList.add(data);
        dataSet.add(data);
        counter++;
    }

    public String getDataById(int id) {
        return dataMap.getOrDefault(id, "Not Found");
    }

    public List<String> getAllData() {
        return new ArrayList<>(dataList);
    }

    public int getDataCount() {
        return counter;
    }

    public boolean removeData(int id) {
        String data = dataMap.remove(id);
        if (data != null) {
            dataList.remove(data);
            dataSet.remove(data);
            counter--;
            return true;
        }
        return false;
    }

    public void printAllData() {
        for (String data : dataList) {
            System.out.println(data);
        }
    }

    public boolean containsData(String data) {
        return dataSet.contains(data);
    }

    public void clearAll() {
        dataMap.clear();
        dataList.clear();
        dataSet.clear();
        counter = 0;
    }

    public void populateSampleData(int n) {
        for (int i = 0; i < n; i++) {
            addData(i, "SampleData" + i);
        }
    }

    public void printStats() {
        System.out.println("Total Data: " + getDataCount());
    }

    public void printDataByPrefix(String prefix) {
        for (String data : dataList) {
            if (data.startsWith(prefix)) {
                System.out.println(data);
            }
        }
    }

    public void printDataBySuffix(String suffix) {
        for (String data : dataList) {
            if (data.endsWith(suffix)) {
                System.out.println(data);
            }
        }
    }

    public void printDataContaining(String substring) {
        for (String data : dataList) {
            if (data.contains(substring)) {
                System.out.println(data);
            }
        }
    }

    public List<String> getDataByPredicate(Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for (String data : dataList) {
            if (predicate.test(data)) {
                result.add(data);
            }
        }
        return result;
    }

    public void sortData() {
        Collections.sort(dataList);
    }

    public void reverseData() {
        Collections.reverse(dataList);
    }

    public void shuffleData() {
        Collections.shuffle(dataList);
    }

    public void printFirstN(int n) {
        for (int i = 0; i < Math.min(n, dataList.size()); i++) {
            System.out.println(dataList.get(i));
        }
    }

    public void printLastN(int n) {
        for (int i = Math.max(0, dataList.size() - n); i < dataList.size(); i++) {
            System.out.println(dataList.get(i));
        }
    }

    public void duplicateData(int id, int times) {
        String data = dataMap.get(id);
        if (data != null) {
            for (int i = 0; i < times; i++) {
                addData(counter, data + "_copy" + i);
            }
        }
    }

    public void printUniqueData() {
        for (String data : dataSet) {
            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        Sample200LineJavaClass sample = new Sample200LineJavaClass();
        sample.populateSampleData(20);

        sample.printAllData();
        System.out.println("Data count: " + sample.getDataCount());

        sample.addData(100, "CustomData");
        sample.printStats();

        sample.printDataByPrefix("Sample");
        sample.printDataBySuffix("5");
        sample.printDataContaining("Data1");

        sample.sortData();
        sample.printFirstN(5);

        sample.reverseData();
        sample.printLastN(5);

        sample.duplicateData(1, 3);
        sample.printAllData();

        sample.shuffleData();
        sample.printAllData();

        System.out.println("Contains 'CustomData': " + sample.containsData("CustomData"));

        sample.removeData(1);
        sample.printAllData();

        sample.clearAll();
        sample.printStats();

        // More dummy operations to reach 200 lines
        for (int i = 0; i < 30; i++) {
            sample.addData(i + 200, "MoreData" + i);
        }
        sample.printAllData();

        System.out.println("Predicate example:");
        List<String> filtered = sample.getDataByPredicate(s -> s.endsWith("9"));
        for (String d : filtered) {
            System.out.println(d);
        }

        // Print unique data
        sample.printUniqueData();

        // Final stats
        sample.printStats();
    }
}

// Helper functional interface for Java 7 compatibility
interface Predicate<T> {
    boolean test(T t);
}
