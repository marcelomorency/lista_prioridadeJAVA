class Produto {
    public String nome;
    public double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

class Nodo<T> {
    T item;
    Nodo<T> proximo;

    public Nodo(T item) {
        this.item = item;
        this.proximo = null;
    }
}

public class FilaPrioridade<T extends Produto> {
    private Nodo<T> primeiro;
    private Nodo<T> ultimo;

    public FilaPrioridade() {
        primeiro = null;
        ultimo = null;
    }

    // Método para inserir em uma Fila de Prioridade Não Ordenada
    public void insere(T item) {
        Nodo<T> novoNodo = new Nodo<>(item);
        if (primeiro == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.proximo = novoNodo;
            ultimo = novoNodo;
        }
    }

    // Método para inserir em uma Fila de Prioridade Ordenada
    public void insereOrdenado(T item) {
        Nodo<T> novoNodo = new Nodo<>(item);
        if (primeiro == null || item.preco > primeiro.item.preco) {
            novoNodo.proximo = primeiro;
            primeiro = novoNodo;
        } else {
            Nodo<T> atual = primeiro;
            while (atual.proximo != null && item.preco < atual.proximo.item.preco) {
                atual = atual.proximo;
            }
            novoNodo.proximo = atual.proximo;
            atual.proximo = novoNodo;
        }
    }

    // Método para remover em uma Fila de Prioridade Não Ordenada
    public T remove() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }

        Nodo<T> atual = primeiro;
        Nodo<T> nodoPrioridadeMaxima = primeiro;
        Nodo<T> nodoAnteriorPrioridadeMaxima = null;

        while (atual != null && atual.proximo != null) {
            if ((atual.proximo.item.preco - nodoPrioridadeMaxima.item.preco) > 0) {
                nodoPrioridadeMaxima = atual.proximo;
                nodoAnteriorPrioridadeMaxima = atual;
            }
            atual = atual.proximo;
        }

        // Verifica se o nó de maior prioridade está no início da fila
        if (nodoAnteriorPrioridadeMaxima == null) {
            primeiro = primeiro.proximo;
        } else {
            nodoAnteriorPrioridadeMaxima.proximo = nodoPrioridadeMaxima.proximo;
        }

        // Verifica se o nó de maior prioridade é o último nó da fila.
        if (nodoPrioridadeMaxima == ultimo) {
            ultimo = nodoAnteriorPrioridadeMaxima;
        }

        return nodoPrioridadeMaxima.item;
    }

    // Método para remover em uma Fila de Prioridade Ordenada
    public T removePrimeiro() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }

        T itemRemovido = primeiro.item;
        primeiro = primeiro.proximo;
        return itemRemovido;
    }


    public boolean estaVazia() {
        return primeiro == null;
    }

    public void imprimir() {
        Nodo<T> atual = primeiro;
        while (atual != null) {
            System.out.println(atual.item.nome + " - Preço: " + atual.item.preco);
            atual = atual.proximo;
        }
    }

    public static void main(String[] args) {
        FilaPrioridade<Produto> fila = new FilaPrioridade<>();

//        // Adicionando produtos à fila de prioridade não ordenada
//        fila.insere(new Produto("Smartphone", 1000));
//        fila.insere(new Produto("Laptop", 1500));
//        fila.insere(new Produto("Tablet", 800));
//        fila.insere(new Produto("Fone de Ouvido", 200));
//        fila.insere(new Produto("Desktop", 1500));
//
//        fila.imprimir();
//        // Removendo produtos (será removido o de maior preço)
//        while (!fila.estaVazia()) {
//            Produto produtoRemovido = fila.remove();
//            System.out.println("Produto removido: " + produtoRemovido.getNome() + " - Preço: R$" + produtoRemovido.getPreco());
//        }

        // Adicionando produtos à Fila de Prioridade Ordenada
        fila.insereOrdenado(new Produto("Smartphone", 1000));
        fila.insereOrdenado(new Produto("Laptop", 1500));
        fila.insereOrdenado(new Produto("Tablet", 800));
        fila.insereOrdenado(new Produto("Fone de Ouvido", 200));
        fila.insereOrdenado(new Produto("Desktop", 1500));

        fila.imprimir();

        // Removendo produtos (será removido o de maior preço)
        while (!fila.estaVazia()) {
            Produto produtoRemovido = fila.removePrimeiro();
            System.out.println("Produto removido: " + produtoRemovido.getNome() + " - Preço: R$" + produtoRemovido.getPreco());
        }

    }
}