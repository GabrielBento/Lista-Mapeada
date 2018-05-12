package listbasedmap;

public interface Map<K, V> {

    // Adiciona um mapeamento de k para v
    public void put(K k, V v);

    // Retorna o valor mapeado por k
    public V get(K k);

    // Remove o mapeamento pela chave k
    public V remove(K k);
}
