package listbasedmap;

import java.util.Objects;

public class ListBasedMapMain {

    public static void main(String[] args) {
        // Inicializa um novo Map
        ListBasedMap<String, Integer> m = new ListBasedMap<>();
        // Maçã -> 30
        m.put("Maçã", 30);
        // Jaca -> 15
        m.put2("Jaca", 15);
        // Melancia -> 1
        m.put("Melancia", 1);

        System.out.println(m);
        System.out.println("Temos " + m.get("Jaca") + " jaca(s)");

        // Jaca -> 0
        m.put("Jaca", 0);
        System.out.println("Agora temos " + m.get("Jaca") + " jaca(s)");

        // remove o mapeamento da chave "Maçã"
        m.remove("Maçã");
        System.out.println(m);

        // Mapeamentos com a chave null são permitidos
        // null -> 2
        m.put(null, 2);
        System.out.println(m.get2(null));

        // Mapeamentos para valores null também
        // Banana -> null
        m.put2("Banana", null);
        System.out.println(m.get("Banana"));
        System.out.println(m);
        
        // Uma consequência importante do uso de mapeamentos para null
        System.out.println(Objects.equals(m.get("Uva"), m.get("Banana")));
        
        // Outra consequência
        // null -> null
        m.put(null, null);
        System.out.println(m);
        
        System.out.println("---");
        
        for (Entry<String, Integer> e : m) {
            System.out.printf("%s -> %d%n", e.first, e.second);
        }
    }
}
