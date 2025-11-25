package com.example.sample;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sample200LineJavaClass {

    private final Map<Integer, String> dataMap;
    private final List<String> dataList;
    private final Set<String> dataSet;
    private int nextId;

    public Sample200LineJavaClass() {
        this.dataMap = new HashMap<>();
        this.dataList = new ArrayList<>();
        this.dataSet = new HashSet<>();
        this.nextId = 0;
    }

    public int addData(String data) {
        int id = nextId++;
        dataMap.put(id, data);
        dataList.add(data);
        dataSet.add(data);
        return id;
    }

    public void addData(int id, String data) {
        dataMap.put(id, data);
        dataList.add(data);
        dataSet.add(data);
        nextId = Math.max(nextId, id + 1);
    }

    public String getDataById(int id) {
        return dataMap.getOrDefault(id, "Not Found");
    }

    public List<String> getAllData() {
        return new ArrayList<>(dataList);
    }

    public int getDataCount() {
        return dataList.size();
    }

    public boolean removeData(int id) {
        String data = dataMap.remove(id);
        if (data != null) {
            dataList.remove(data);
            dataSet.remove(data);
            return true;
        }
        return false;
    }

    public void printAllData() {
        dataList.forEach(System.out::println);
    }

    public boolean containsData(String data) {
        return dataSet.contains(data);
    }

    public void clearAll() {
        dataMap.clear();
        dataList.clear();
        dataSet.clear();
        nextId = 0;
    }

    public void populateSampleData(int n) {
        for (int i = 0; i < n; i++) {
            addData(i, "SampleData" + i);
        }
    }

    public void printStats() {
        System.out.println("Total Data: " + getDataCount());
    }

    public List<String> filterData(Predicate<String> predicate) {
        return dataList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public void printFilteredData(Predicate<String> predicate) {
        filterData(predicate).forEach(System.out::println);
    }

    public void printDataByPrefix(String prefix) {
        printFilteredData(data -> data.startsWith(prefix));
    }

    public void printDataBySuffix(String suffix) {
        printFilteredData(data -> data.endsWith(suffix));
    }

    public void printDataContaining(String substring) {
        printFilteredData(data -> data.contains(substring));
    }

    public List<String> getDataByPredicate(Predicate<String> predicate) {
        return filterData(predicate);
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
        dataList.stream().limit(n).forEach(System.out::println);
    }

    public void printLastN(int n) {
        dataList.stream()
                .skip(Math.max(0, dataList.size() - n))
                .forEach(System.out::println);
    }

    public void duplicateData(int id, int times) {
        String data = dataMap.get(id);
        if (data != null) {
            for (int i = 0; i < times; i++) {
                addData(data + "_copy" + i);
            }
        }
    }

    public void printUniqueData() {
        dataSet.forEach(System.out::println);
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

        // Populate more sample data
        for (int i = 0; i < 30; i++) {
            sample.addData(i + 200, "MoreData" + i);
        }
        sample.printAllData();

        System.out.println("Predicate example:");
        sample.getDataByPredicate(s -> s.endsWith("9")).forEach(System.out::println);

        sample.printUniqueData();
        sample.printStats();
    }
}
