package listbasedmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ListBasedMap<K, V> implements Map<K, V>, Iterable<Entry<K, V>> {

    /* O atributo table é na realidade uma lista de Entries (pares) nos quais
       o primeiro representa uma chave e o segundo um valor "mapeado" por esta
       chave.
     */
    private List<Entry<K, V>> table = new ArrayList<>();

    /* Coloca o valor value com a chave key na tabela. Se já existe um valor
       mapeado pela mesma chave então este é substituido pela nova (value).
       Caso contrário uma nova Entry é inserida com o mapeamento.
     */
    @Override
    public void put(K key, V value) {
        // -1 se não houver um valor com esta chave ou um índice caso contrário
        int i = findIndex(key);
        if (i == -1) {
            table.add(new Entry<>(key, value));
        } else {
            Entry<K, V> e = table.get(i);
            e.second = value;
        }
    }

    // Versão alternativa do método put que utiliza Streams, lambdas e optionals
    public void put2(K key, V value) {
        table.stream()
                .filter(e -> Objects.equals(e.first, key))
                .findFirst()
                .map(e -> {
                    e.second = value;
                    return e;
                }).orElseGet(() -> {
            Entry<K, V> e = new Entry<>(key, value);
            table.add(e);
            return e;
        });
    }

    /* Encontra e retorna o índice da Entry que contém a chave key se ela
       existir. Caso contrário retorna -1.
     */
    private int findIndex(K key) {
        int size = table.size();
        for (int i = 0; i < size; i++) {
            Entry<K, V> e = table.get(i);
            if (e != null) {
                // A condição como escrita abaixo falha quando a chave é null
                // if (e.first.equals(key)) {
                // O método estático equals abaixo é null-safe
                if (Objects.equals(e.first, key)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /* retorna o valor mapeado pela chave key se o mapeamento existir ou null
       caso contrário.
     */
    @Override
    public V get(K key) {
        int i = findIndex(key);
        if (i == -1) {
            return null;
        } else {
            return table.get(i).second;
        }
    }

    // Versão alternativa do método get que utiliza streams, lambdas e optionals
    public V get2(K key) {
        return table.stream()
                .filter(e -> Objects.equals(e.first, key))
                .findFirst()
                .map(e -> e.second)
                .orElse(null);
    }

    /* Remove a Entry que contém a chave key de table e retorna o valor mapeado
       se este existir. Caso contrário retorna null.
     */
    @Override
    public V remove(K key) {
        int i = findIndex(key);
        if (i == -1) {
            return null;
        } else {
            return table.remove(i).second;
        }
    }

    // O tamanho deste map é igual ao tamanho de Entries em table.
    public int size() {
        return table.size();
    }

    @Override
    public String toString() {
        return table.toString();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return table.iterator();
    }

}
