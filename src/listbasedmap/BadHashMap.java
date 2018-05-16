package listbasedmap;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/* Este HashMap *não* trata os casos onde há colisão de hashes.
 */
public class BadHashMap<K, V> implements Map<K, V> {

    /* Um array de Entries que possuem, cada uma, dois valores representando uma
       chave e um valor mapeado por esta chave.
     */
    Entry[] table;

    // table é inicializada com tamanho size
    public BadHashMap(int size) {
        table = new Entry[size];
    }

    /* O índice onde a Entry será inserida é calculado fazendo o uso do hashcode
       da chave em questão.
     */
    @Override
    public void put(K key, V val) {
        // O hashCode da chave. A forma abaixo não funciona quando key é null
        //int hash = key.hashCode();
        // Em vez disso utilizamos o método estático null-safe abaixo
        int hash = Objects.hashCode(key);
        /* Transforma o hashCode (que pode ser um inteiro qualquer, até mesmo
           negativo) em um índice válido no intervalo [0, table.length - 1]
         */
        int index = Math.abs(hash % table.length);
        // Se o valor no índice index for nulo, insere o mapeamento neste índice
        Entry<K, V> e = table[index];
        if (e == null) {
            table[index] = new Entry<>(key, val);
            // Caso contrário há uma colisão entre as hashes.
        } else {
            throw new RuntimeException("Colisão de índice para a hash " + hash);
        }
    }

    /* Esta versão utiliza streams e funções de alta ordem. Imprime apenas as
       Entries que não nulas no array table.
     */
    @Override
    public String toString() {
        // Obtém uma stream do array table
        return Arrays.stream(table)
                // filtra (descarta) os objetos nulos
                .filter(Objects::nonNull)
                // transforma os remanescentes em String
                .map(Entry::toString)
                // junta todas as Strings separadas por vírgula
                .collect(Collectors.joining(", "));
    }

    @Override
    public V get(K key) {
        // Ver método put para descrição do cálculo da hash
        int hash = Objects.hashCode(key);
        int index = Math.abs(hash % table.length);
        // Obtém a (possivelmente nula) Entry localizada 
        Entry<K, V> e = table[index];
        // Retorna null se a Entry for null ou o valor mapeado caso contrário
        return e != null ? e.second : null;
    }

    @Override
    public V remove(K key) {
        // Ver método put para descrição do cálculo da hash
        int hash = key.hashCode();
        int index = Math.abs(hash % table.length);
        // Obtém a (possivelmente nula) Entry localizada 
        Entry<K, V> e = table[index];
        // Remove o valor do array table
        table[index] = null;
        // Retorna o valor mapeado se a Entry não for nula
        return e == null ? null : e.second;
    }
}
