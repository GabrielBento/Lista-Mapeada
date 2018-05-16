package listbasedmap;

public class BadHashMapMain {

    public static void main(String[] args) {
        // Inicializa um novo HashMap com capacidade 10
        BadHashMap<String, Integer> m = new BadHashMap<>(10);
        // Maçã -> 30
        m.put("Maçã", 30);
        // Jaca -> 15
        m.put("Jaca", 15);
        // Melancia -> 1
        m.put("Melancia", 1);

        System.out.println(m);

        // No HashMap com capacidade 10, a String banana causa uma colisão
        try {
            m.put("Banana", 20);
        } catch (RuntimeException e) {
            System.err.println("Mapeamento não inserido. Motivo: " + e.getMessage());
        }

        // Uma grave consequência
        System.out.println(m.get("Banana"));
    }
}
