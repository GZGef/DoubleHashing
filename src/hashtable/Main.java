package hashtable;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();

        System.out.println(hashMap.isEmpty());

        hashMap.put(1, "один");
        hashMap.put(2, "два");
        hashMap.put(3, "три");
        hashMap.put(4, "четыре");
        hashMap.put(5, "пять");
        hashMap.put(5, "шесть");

        System.out.println(hashMap);

        hashMap.remove(1);
        hashMap.remove(3);

        System.out.println(hashMap);
        System.out.println(hashMap.get(2));
        System.out.println(hashMap.getSize());
    }
}
