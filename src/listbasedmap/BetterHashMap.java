package listbasedmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/* Este HashMap trata os casos onde há colisão de hashes.
 */
public class BetterHashMap<K, V> implements Map<K, V> {

    /* 
     */
    List<Entry<K, V>>[] buckets;

    // table é inicializada com tamanho size
    public BetterHashMap(int size) {
        buckets = new ArrayList[size];
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
        int index = Math.abs(hash % buckets.length);
        // Se o valor no índice index for nulo, o bucket ainda não foi incializado
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        List<Entry<K, V>> bucket = buckets[index];
        // Algum valor neste bucket pode ser igual a key
        for (Entry<K, V> e : bucket) {
            // A chave key já existe no bucket. O seu valor será substituído
            if (e.first.equals(key)) {
                e.second = val;
                return;
            }
        }
        // A chave não existe no bucket e será inserida
        bucket.add(new Entry<>(key, val));
    }

    // Retorna o valor mapeado pela chave key se existir um mapeamento com ela
    @Override
    public V get(K key) {
        // Ver método put acima
        int hash = Objects.hashCode(key);
        int index = Math.abs(hash % buckets.length);

        // Se o bucket está vazio então a chave não pode estar presente
        if (buckets[index] == null) {
            return null;
        } else {
            List<Entry<K, V>> bucket = buckets[index];
            // A chave possilvemente estará em um dos valores neste bucket
            for (Entry<K, V> e : bucket) {
                // A chave foi encontrada
                if (Objects.equals(e.first, key)) {
                    return e.second;
                }
            }
        }
        // Chave não encontrada
        return null;
    }
    
    public V get2(K key) {
        int hash = Objects.hashCode(key);
        int index = Math.abs(hash % buckets.length);
        
        return Optional.ofNullable(buckets[index])
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(e -> Objects.equals(e.first, key))
                .findFirst()
                .map(e -> e.second)
                .orElse(null);
    }

    /* Esta versão utiliza streams e funções de alta ordem. Imprime apenas as
       Entries que não nulas no array table.
     */
    @Override
    public String toString() {
        // Obtém uma stream do array table
        return Arrays.stream(buckets)
                // filtra (descarta) os objetos nulos
                .filter(Objects::nonNull)
                // transforma os remanescentes em String
                .map(List::toString)
                // junta todas as Strings separadas por vírgula
                .collect(Collectors.joining(", "));
    }

    @Override
    public V remove(K key) {
        // Ver método put
        int hash = Objects.hashCode(key);
        int index = Math.abs(hash % buckets.length);
        if (buckets[index] == null) {
            return null;
        } else {
            List<Entry<K, V>> bucket = buckets[index];
            // Procura pela chave key no bucket
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> e = bucket.get(i);
                if (Objects.equals(e.first, key)) {
                    // Chave encontrada no bucket
                    bucket.remove(i);
                    return e.second;
                }
            }
            // Chave key não foi encontrada
            return null;
        }
    }
    
    public void clear(){
        for(int i = 0; i < buckets.length; i++){
            if(buckets[i] == null)
                continue;
            buckets[i] = null;
        }
    }
    
    public boolean containsKey(K key){
        int hash = Objects.hashCode(key);
        int index = Math.abs(hash % buckets.length);
        
        if(buckets[index] == null)
            return false;
        
        for(Entry<K, V> t : buckets[index]){
            if(Objects.equals(t.first, key))
                return true;
        }
        
        return  false;
    }
    
   public boolean containsValue(V val){
        for(List<Entry<K, V>> t : buckets){
            if(t == null)
                continue;
            
            for (Entry<K, V> e : t) {
                if (Objects.equals(e.second, val)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isEmpty(){
        for(int i = 0; i < buckets.length; i++){
            if(buckets[i] == null)
                continue;
            return false;
        }
        
        return true;
    }
    
    public V getOrDefault(K key, V def){return null;}

    
    
    

}
