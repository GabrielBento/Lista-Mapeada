package listbasedmap;

import java.util.Objects;

public class BetterHashMapMain {

    public static void main(String[] args) {
        // Inicializa um novo HashMap com capacidade 10
        BetterHashMap<String, Integer> m = new BetterHashMap<>(10);

        // Maçã -> 30
        m.put("Maçã", 30);
        // Jaca -> 15
        m.put("Jaca", 15);
        // Melancia -> 1
        m.put("Melancia", 1);

        System.out.println(m);

        /* Para o HashMap em questão, a colisão é tratada e o mapeamento Banana
           -> 20 é inserido no mesmo bucket do mapeamento Jaca -> 15
        */
        m.put("Banana", 20);
        
        System.out.println(m);

        System.out.println("Temos " + m.get("Maçã") + " maçã(s)");
        System.out.println("Temos " + m.get("Banana") + " banana(s)");
        System.out.println("Temos " + m.get("Jaca") + " jaca(s)");
        // A chave Uva não existe e o método get retorna null
        System.out.println("Temos " + m.get("Uva") + " uva(s)");
        
        // Mapeamentos com a chave null são permitidos
        // null -> 2
        m.put(null, 2);
        System.out.println(m.get(null));

        // Mapeamentos para valores null também
        // Banana - null
        m.put("Banana", null);
        System.out.println(m.get("Banana"));
        System.out.println(m);
        
        // Uma consequência importante do uso de mapeamentos para null
        System.out.println(Objects.equals(m.get("Uva"), m.get("Banana")));
    }

}
